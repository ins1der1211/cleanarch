package ins1der.cleanarch.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ins1der.cleanarch.data.models.db.PersonEntity

class PeopleDataSourceFactory(private val peopleApiService: PeopleApiService):
    DataSource.Factory<String, PersonEntity>() {

    val sourceLiveData = MutableLiveData<PeopleDataSource>()

    override fun create(): DataSource<String, PersonEntity> {
        val source = PeopleDataSource(peopleApiService)
        sourceLiveData.postValue(source)
        return source
    }
}