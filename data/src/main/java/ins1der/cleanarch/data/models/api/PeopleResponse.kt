package ins1der.cleanarch.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ins1der.cleanarch.domain.models.Person

@JsonClass(generateAdapter = true)
data class PeopleResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val nextPage: String? = null,
    @field:Json(name = "previous") val prevPage: String? = null,
    @field:Json(name = "results") val people: List<PersonAPI>
)

@JsonClass(generateAdapter = true)
data class PersonAPI(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)

fun PersonAPI.mapToDomain(): Person = Person(
    name = name,
    url = url
)