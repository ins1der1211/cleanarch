package ins1der.cleanarch.presentation.ui.models

import android.os.Parcelable
import ins1der.cleanarch.data.models.api.PersonAPI
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonUI(
    val name: String,
    val url: String
): Parcelable

fun PersonAPI.mapToUI(): PersonUI = PersonUI(
    name = name,
    url = url
)