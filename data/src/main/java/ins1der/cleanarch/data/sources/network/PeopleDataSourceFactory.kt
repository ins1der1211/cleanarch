package ins1der.cleanarch.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ins1der.cleanarch.domain.models.Person

class PeopleDataSourceFactory<T>(private val peopleApiService: PeopleApiService,
                                 private val mapFunc: (Person) -> T,
                                 private val processFunc: (List<Person>) -> List<Person>):
    DataSource.Factory<String, T>() {

    val sourceLiveData = MutableLiveData<PeopleDataSource<T>>()

    override fun create(): DataSource<String, T> {
        val source = PeopleDataSource(peopleApiService, mapFunc, processFunc)
        sourceLiveData.postValue(source)
        return source
    }
}