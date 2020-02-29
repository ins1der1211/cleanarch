package ins1der.gifmaker.presentation.ui.models

import android.os.Parcelable
import ins1der.gifmaker.domain.models.Person
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