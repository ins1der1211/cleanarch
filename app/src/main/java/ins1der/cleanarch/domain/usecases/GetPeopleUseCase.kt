package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PeopleRepository

class GetPeopleUseCase(private val peopleRepository: PeopleRepository) {

    fun <T> execute(pageSize: Int, initSize: Int): Result<T> =
        peopleRepository.getPeople(pageSize, initSize)
}