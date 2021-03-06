package ins1der.gifmaker.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ins1der.gifmaker.data.models.db.PlanetEntity
import ins1der.gifmaker.domain.models.Planet

@JsonClass(generateAdapter = true)
data class PlanetsResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val nextPage: String?,
    @field:Json(name = "previous") val prevPage: String?,
    @field:Json(name = "results") val planets: List<PlanetAPI>
)

@JsonClass(generateAdapter = true)
data class PlanetAPI(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "population") val population: String,
    @field:Json(name = "surface_water") val surfaceWater: String
)

fun PlanetAPI.mapToDomain(): Planet = Planet(
    name = name,
    population = population,
    surfaceWater = surfaceWater
)

fun PlanetAPI.mapToDb(): PlanetEntity = PlanetEntity(
    name = name,
    surfaceWater = surfaceWater,
    population = population
)