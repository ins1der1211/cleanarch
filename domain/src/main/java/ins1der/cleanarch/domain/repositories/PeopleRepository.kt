package ins1der.cleanarch.domain.repositories

interface PeopleRepository<T> {

    fun <R> getPeople(pageSize: Int, initSize: Int): Result<R>
}