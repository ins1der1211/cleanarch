package ins1der.gifmaker.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ins1der.gifmaker.domain.models.Planet

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val population: String,
    val surfaceWater: String
)

fun PlanetEntity.mapToDomain(): Planet = Planet(
    name = name,
    population = population,
    surfaceWater = surfaceWater
)