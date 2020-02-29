package ins1der.gifmaker.domain.repositories

import ins1der.gifmaker.domain.models.Listing
import ins1der.gifmaker.domain.models.Person

interface PeopleRepository<T> {

    fun getPeoplePaged(pageSize: Int, initSize: Int, processFunc: (List<Person>) -> List<Person>): Result<Listing<T>>
}