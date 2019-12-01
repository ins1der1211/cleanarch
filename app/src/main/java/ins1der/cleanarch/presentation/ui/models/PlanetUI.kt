package ins1der.cleanarch.presentation.ui.models

import android.os.Parcelable
import ins1der.cleanarch.domain.models.Planet
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetUI(
    val name: String,
    val population: String,
    val surfaceWater: String
): Parcelable

fun Planet.mapToUI(): PlanetUI = PlanetUI(
    name = name,
    surfaceWater = surfaceWater,
    population = population
)