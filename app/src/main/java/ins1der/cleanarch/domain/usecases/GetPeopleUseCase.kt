package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.repositories.PeopleRepository

class GetPeopleUseCase(private val peopleRepository: PeopleRepository) {

    suspend fun execute(): Result<List<Person>> {
        val result = peopleRepository.getPeople()
        if (result.isSuccess) {
            // do some operation on result list if needed, for example sorting, etc
            return Result.success(result.getOrDefault(listOf()))
        }
        return result
    }
}