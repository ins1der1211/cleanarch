package ins1der.cleanarch.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.StringBuilder


class MainActivity: BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.employees.observe(this, Observer {
            it?.let {
                with (StringBuilder()) {
                    it.forEach {
                        append(it.toString())
                        append("\n")
                    }
                    response_tv?.text = toString()
                }
            }
        })
    }
}