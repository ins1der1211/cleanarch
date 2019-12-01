package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.data.sources.db.DbDataSource
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.repositories.PeopleRepository

class PeopleRepositoryImpl(private val apiDataSource: ApiDataSource,
                           private val dbDataSource: DbDataSource): PeopleRepository {

    override suspend fun getPeople(): Result<List<Person>> {
        return Result.error()
    }

}