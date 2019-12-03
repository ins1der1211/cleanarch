package ins1der.cleanarch.presentation.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonUI(
    val name: String,
    val url: String
): Parcelable