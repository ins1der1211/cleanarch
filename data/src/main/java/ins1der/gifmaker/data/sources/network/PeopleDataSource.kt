package ins1der.gifmaker.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import ins1der.gifmaker.domain.models.PageState
import ins1der.gifmaker.data.models.api.mapToDomain
import ins1der.gifmaker.data.utils.successBody
import ins1der.gifmaker.domain.models.Person
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class PeopleDataSource<T>(private val peopleApiService: PeopleApiService,
                          private val mapFunc: (Person) -> T,
                          private val processFunc: (List<Person>) -> List<Person>):
    PageKeyedDataSource<String, T>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    var retry: (() -> Any)? = null
    val pageState = MutableLiveData<PageState>()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, T>) {
        Timber.d("loadInitial called")
        runBlocking(
            block = {
                pageState.postValue(PageState.LOADING)
                val result = peopleApiService.getPeople(1)
                retry = null
                if (result.isSuccessful) {
                    val resp = result.successBody()
                    pageState.postValue(PageState.LOADED)
                    val apiList = resp.people
                    // possibility to apply domain logic with mapping by single item
                    val domainList1 = apiList.map { it.mapToDomain() }
                    // possibility to apply domain logic with processing by page
                    val domainList2 = processFunc(domainList1)
                    // after processing on domain layer get the final result
                    val tList = domainList2.map(mapFunc)
                    callback.onResult(tList, resp.prevPage ?: "", resp.nextPage ?: "")
                } else {
                    pageState.postValue(PageState.error(null))
                }
            },
            context = CoroutineExceptionHandler { _, t ->
                pageState.postValue(PageState.error(t.message))
                retry = { loadInitial(params, callback) }
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, T>) {
        Timber.d("loadAfter called with key -> ${params.key}")
        runBlocking(
            block = {
                if (params.key.isEmpty()) return@runBlocking
                else {
                    pageState.postValue(PageState.LOADING)
                    val result = peopleApiService.getPeople(params.key.split("=")[1].toInt())
                    retry = null
                    if (result.isSuccessful) {
                        val resp = result.successBody()
                        pageState.postValue(PageState.LOADED)
                        val apiList = resp.people
                        // possibility to apply domain logic with mapping by single item
                        val domainList1 = apiList.map { it.mapToDomain() }
                        // possibility to apply domain logic with processing by page
                        val domainList2 = processFunc(domainList1)
                        // after processing on domain layer get the final result
                        val tList = domainList2.map(mapFunc)
                        callback.onResult(tList, resp.nextPage ?: "")
                    } else {
                        pageState.postValue(PageState.error(null))
                    }
                }
            },
            context = CoroutineExceptionHandler { _, t ->
                pageState.postValue(PageState.error(t.message))
                retry = { loadAfter(params, callback) }
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, T>) {}

}