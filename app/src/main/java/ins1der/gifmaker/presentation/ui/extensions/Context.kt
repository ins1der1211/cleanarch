package ins1der.gifmaker.presentation.ui.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import ins1der.gifmaker.presentation.ui.base.BaseActivity

fun Context.toast(content: Any, length: Int = Toast.LENGTH_SHORT): Toast {
    val message = when (content) {
        is String -> content
        is Int -> getString(content)
        else -> throw IllegalArgumentException("Content must be a String or a String resource id.")
    }

    return Toast.makeText(this, message, length).also { it.show() }
}

inline fun<reified T> Context.startActivity() where T: BaseActivity {
    startActivity(Intent(this, T::class.java))
}