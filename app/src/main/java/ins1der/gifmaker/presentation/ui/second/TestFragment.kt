package ins1der.gifmaker.presentation.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ins1der.gifmaker.R
import ins1der.gifmaker.presentation.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class TestFragment: BaseFragment() {

    private val secondViewModel: SecondViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$secondViewModel")
    }
}