package ins1der.cleanarch.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import ins1der.cleanarch.data.models.NetworkState
import ins1der.cleanarch.data.models.api.mapToDb
import ins1der.cleanarch.data.models.db.PersonEntity
import ins1der.cleanarch.data.utils.successBody
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PeopleDataSource(private val peopleApiService: PeopleApiService):
    PageKeyedDataSource<String, PersonEntity>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PersonEntity>) {
        networkState.postValue(NetworkState.LOADING)
        launch(
            block = {
                val result = peopleApiService.getPeople(1)
                retry = null
                if (result.isSuccessful) {
                    networkState.postValue(NetworkState.LOADED)
                    callback.onResult(result.successBody().people.map { it.mapToDb() },
                        result.successBody().prevPage ?: "", result.successBody().nextPage ?: "")
                } else {
                    networkState.postValue(NetworkState.error(null))
                }
            },
            context = CoroutineExceptionHandler { _, t ->
                networkState.postValue(NetworkState.error(t.message))
                retry = { loadInitial(params, callback) }
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PersonEntity>) {
        networkState.postValue(NetworkState.LOADING)
        launch(
            block = {
                if (params.key.isEmpty()) return@launch
                else {
                    val result = peopleApiService.getPeople(params.key.split("=")[1].toInt())
                    retry = null
                    if (result.isSuccessful) {
                        networkState.postValue(NetworkState.LOADED)
                        callback.onResult(result.successBody().people.map { it.mapToDb() },
                            result.successBody().nextPage ?: "")
                    } else {
                        networkState.postValue(NetworkState.error(null))
                    }
                }
            },
            context = CoroutineExceptionHandler { _, t ->
                networkState.postValue(NetworkState.error(t.message))
                retry = { loadAfter(params, callback) }
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PersonEntity>) {}

}