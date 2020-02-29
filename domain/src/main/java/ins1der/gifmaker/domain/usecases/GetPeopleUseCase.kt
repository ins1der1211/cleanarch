package ins1der.gifmaker.domain.usecases

import ins1der.gifmaker.domain.models.Listing
import ins1der.gifmaker.domain.models.Person
import ins1der.gifmaker.domain.repositories.PeopleRepository

class GetPeopleUseCase<T>(private val peopleRepository: PeopleRepository<T>) {

    fun execute(pageSize: Int, initSize: Int): Result<Listing<T>> =
        peopleRepository.getPeoplePaged(pageSize, initSize) { sortPeople(it) }
}

// example process func
fun sortPeople(people: List<Person>): List<Person> {
    return people.sortedByDescending { it.url.hashCode() }
}