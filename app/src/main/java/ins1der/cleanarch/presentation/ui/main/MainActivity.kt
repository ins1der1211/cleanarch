package ins1der.cleanarch.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseActivity
import ins1der.cleanarch.presentation.ui.extensions.startActivity
import ins1der.cleanarch.presentation.ui.extensions.toast
import ins1der.cleanarch.presentation.ui.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.StringBuilder


class MainActivity: BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewStateObserver()
        if (savedInstanceState == null) mainViewModel.getPlanets()
        response_tv.setOnClickListener { mainViewModel.getPlanets() }
        open_second_fab.setOnClickListener { startActivity<SecondActivity>() }
    }

    private fun initViewStateObserver() {
        mainViewModel.viewState.observe(this, Observer {
            Timber.d("view state observe called")
            it.planets?.let {
                with (StringBuilder()) {
                    it.forEach { append(it.toString()).append("\n") }
                    response_tv?.text = toString()
                }
            }
            it.error?.let { toast(it) }
        })
    }
}