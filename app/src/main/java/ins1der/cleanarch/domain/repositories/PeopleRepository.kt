package ins1der.cleanarch.domain.repositories

import ins1der.cleanarch.domain.models.Person

interface PeopleRepository {

    suspend fun getPeople(): Result<List<Person>>
}