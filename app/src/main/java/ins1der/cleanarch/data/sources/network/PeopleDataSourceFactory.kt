package ins1der.cleanarch.data.sources.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ins1der.cleanarch.presentation.ui.models.PersonUI

class PeopleDataSourceFactory(private val peopleApiService: PeopleApiService):
    DataSource.Factory<String, PersonUI>() {

    val sourceLiveData = MutableLiveData<PeopleDataSource>()

    override fun create(): DataSource<String, PersonUI> {
        val source = PeopleDataSource(peopleApiService)
        sourceLiveData.postValue(source)
        return source
    }
}