package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.common.ResultType
import ins1der.cleanarch.domain.models.Person
import ins1der.cleanarch.domain.repositories.PeopleRepository

class GetPeopleUseCase(private val peopleRepository: PeopleRepository) {

    suspend fun execute(): Result<List<Person>> {
        val result = peopleRepository.getPeople()
        return if (result.resultType == ResultType.SUCCESS) {
            // do some operation on result list if needed, for example sorting, etc
            Result.success(result.data)
        } else {
            Result.error(result.error)
        }
    }
}