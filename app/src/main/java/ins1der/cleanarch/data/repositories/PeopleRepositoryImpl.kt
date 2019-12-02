package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.repositories.PeopleRepository

class PeopleRepositoryImpl(private val peopleApiService: PeopleApiService): PeopleRepository {

    override suspend fun getPeople(): Result<List<Person>> {
        return Result.failure(Throwable())
    }

}