package ins1der.cleanarch.presentation.ui.second

import android.os.Bundle
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class SecondActivity: BaseActivity() {

    private val secondViewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Timber.d("$secondViewModel")
    }
}