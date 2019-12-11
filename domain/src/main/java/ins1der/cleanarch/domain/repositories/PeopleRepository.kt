package ins1der.cleanarch.domain.repositories

import ins1der.cleanarch.domain.models.Listing
import ins1der.cleanarch.domain.models.Person

interface PeopleRepository<T> {

    fun getPeoplePaged(pageSize: Int, initSize: Int, processFunc: (List<Person>) -> List<Person>): Result<Listing<T>>
}