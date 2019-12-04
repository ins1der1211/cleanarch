package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PeopleRepository

class GetPeopleUseCase<T>(private val peopleRepository: PeopleRepository<T>) {

    fun <R> execute(pageSize: Int, initSize: Int): Result<R> =
        peopleRepository.getPeople(pageSize, initSize)
}