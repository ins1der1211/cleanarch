package ins1der.cleanarch.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ins1der.cleanarch.domain.models.Planet

@JsonClass(generateAdapter = true)
data class PlanetsResponse(
    val planets: List<PlanetAPI>
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