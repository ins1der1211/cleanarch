package ins1der.gifmaker.presentation.ui.base

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment: Fragment(), CoroutineScope by MainScope() {

    override fun onDetach() {
        super.onDetach()
        cancel()
    }
}