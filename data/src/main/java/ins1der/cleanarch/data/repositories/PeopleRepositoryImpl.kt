package ins1der.cleanarch.data.repositories

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import ins1der.cleanarch.data.models.Listing
import ins1der.cleanarch.data.models.api.PersonAPI
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PeopleDataSourceFactory
import ins1der.cleanarch.domain.repositories.PeopleRepository

class PeopleRepositoryImpl<T>(private val peopleApiService: PeopleApiService,
                           private val mapFunc: (PersonAPI) -> T): PeopleRepository<T> {

    override fun <R> getPeople(pageSize: Int, initSize: Int): Result<R> {
        val sourceFactory = PeopleDataSourceFactory(peopleApiService, mapFunc)
        val pagedList =
            LivePagedListBuilder<String, T>(sourceFactory,
                10 /* api doesn't support page size */).build()
        return Result.success(Listing(
            pagedList = pagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState},
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )) as Result<R>
    }
}