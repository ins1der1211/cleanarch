package ins1der.cleanarch.data.repositories

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import ins1der.cleanarch.data.models.Listing
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PeopleDataSourceFactory
import ins1der.cleanarch.domain.repositories.PeopleRepository
import ins1der.cleanarch.presentation.ui.models.PersonUI

class PeopleRepositoryImpl(private val peopleApiService: PeopleApiService): PeopleRepository {

    override fun <T> getPeople(pageSize: Int, initSize: Int): Result<T> {
        val sourceFactory = PeopleDataSourceFactory(peopleApiService)
        val pagedList =
            LivePagedListBuilder<String, PersonUI>(sourceFactory,
                10 /* api doesn't support page size */).build()
        return Result.success(Listing(
            pagedList = pagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState},
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )) as Result<T>
    }
}