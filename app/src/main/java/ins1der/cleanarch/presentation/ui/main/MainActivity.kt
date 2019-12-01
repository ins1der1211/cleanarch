package ins1der.cleanarch.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseActivity
import ins1der.cleanarch.presentation.ui.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.StringBuilder


class MainActivity: BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewStateObserver()
        mainViewModel.getPlanets()
        response_tv.setOnClickListener { mainViewModel.getPlanets() }
    }

    private fun initViewStateObserver() {
        mainViewModel.viewState.observe(this, Observer {
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