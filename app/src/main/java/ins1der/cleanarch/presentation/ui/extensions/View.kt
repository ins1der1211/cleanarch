package ins1der.cleanarch.presentation.ui.extensions

import android.view.View
import android.view.View.*

fun View.visible() {
    visibility = VISIBLE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.gone() {
    visibility = GONE
}