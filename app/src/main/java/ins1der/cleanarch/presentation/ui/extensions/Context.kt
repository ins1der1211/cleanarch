package ins1der.cleanarch.presentation.ui.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(content: Any, length: Int = Toast.LENGTH_SHORT): Toast {
    val message = when (content) {
        is String -> content
        is Int -> getString(content)
        else -> throw IllegalArgumentException("Content must be a String or a String resource id.")
    }

    return Toast.makeText(this, message, length).also { it.show() }
}