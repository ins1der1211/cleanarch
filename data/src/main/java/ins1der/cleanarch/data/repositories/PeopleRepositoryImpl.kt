package ins1der.cleanarch.data.repositories

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import ins1der.cleanarch.domain.models.Listing
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PeopleDataSourceFactory
import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.repositories.PeopleRepository

class PeopleRepositoryImpl<T>(private val peopleApiService: PeopleApiService,
                              private val mapFunc: (Person) -> T): PeopleRepository<T> {

    override fun getPeoplePaged(pageSize: Int,
                                initSize: Int,
                                processFunc: (List<Person>) -> List<Person>): Result<Listing<T>> {
        val sourceFactory = PeopleDataSourceFactory(peopleApiService, mapFunc, processFunc)
        val pagedList =
            LivePagedListBuilder<String, T>(sourceFactory,
                10 /* api doesn't support page size */).build()
        return Result.success(Listing(
            pagedList = pagedList,
            pageState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.pageState },
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
            retry = { sourceFactory.sourceLiveData.value?.retry?.invoke() }
        ))
    }
}