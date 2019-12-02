package ins1der.cleanarch.domain.repositories

interface PeopleRepository {

    suspend fun <T> getPeople(pageSize: Int, initSize: Int): Result<T>
}