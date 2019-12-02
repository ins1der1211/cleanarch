package ins1der.cleanarch.data.repositories

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import ins1der.cleanarch.data.models.Listing
import ins1der.cleanarch.data.models.db.PersonEntity
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PeopleDataSourceFactory
import ins1der.cleanarch.domain.repositories.PeopleRepository

class PeopleRepositoryImpl(private val peopleApiService: PeopleApiService): PeopleRepository {

    override suspend fun <T> getPeople(pageSize: Int, initSize: Int): Result<T> {
        val sourceFactory = PeopleDataSourceFactory(peopleApiService)
        val pagedList =
            LivePagedListBuilder<String, PersonEntity>(sourceFactory,
                1 /* api doesn't support page size */).build()
        return Result.success(Listing(
            pagedList = pagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState},
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        )) as Result<T>
    }
}