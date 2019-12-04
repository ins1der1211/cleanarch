package ins1der.cleanarch.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ins1der.cleanarch.data.models.api.PersonAPI

class PeopleDataSourceFactory<T>(private val peopleApiService: PeopleApiService,
                                 private val mapFunc: (PersonAPI) -> T):
    DataSource.Factory<String, T>() {

    val sourceLiveData = MutableLiveData<PeopleDataSource<T>>()

    override fun create(): DataSource<String, T> {
        val source = PeopleDataSource(peopleApiService, mapFunc)
        sourceLiveData.postValue(source)
        return source
    }
}