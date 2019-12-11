package ins1der.cleanarch.presentation.ui.models

import android.os.Parcelable
import ins1der.cleanarch.data.models.api.PersonAPI
import ins1der.cleanarch.domain.models.Person
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonUI(
    val name: String,
    val url: String
): Parcelable

fun Person.mapToUI(): PersonUI = PersonUI(
    name = name,
    url = url
)