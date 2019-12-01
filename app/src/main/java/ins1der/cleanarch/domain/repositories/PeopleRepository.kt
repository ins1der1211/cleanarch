package ins1der.cleanarch.domain.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.models.Planet

interface PeopleRepository {

    suspend fun getPeople(): Result<List<Person>>
}