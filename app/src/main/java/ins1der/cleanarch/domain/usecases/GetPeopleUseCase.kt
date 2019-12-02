package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PeopleRepository

class GetPeopleUseCase(private val peopleRepository: PeopleRepository) {

    suspend fun <T> execute(): Result<T> = peopleRepository.getPeople()
}