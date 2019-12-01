package ins1der.cleanarch.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.base.BaseActivity
import ins1der.cleanarch.presentation.ui.extensions.startActivity
import ins1der.cleanarch.presentation.ui.extensions.toast
import ins1der.cleanarch.presentation.ui.second.SecondActivity
import ins1der.cleanarch.workers.TestWorker
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.StringBuilder
import java.util.*


class MainActivity: BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewStateObserver()
        if (savedInstanceState == null) mainViewModel.getPlanets()
        response_tv.setOnClickListener { mainViewModel.getPlanets() }
        open_second_fab.setOnClickListener { startActivity<SecondActivity>() }
        startWork()
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

    private fun startWork() {
        val constraints = Constraints.Builder().build()
        val tag = UUID.randomUUID().toString()
        val work = OneTimeWorkRequestBuilder<TestWorker>()
            .setConstraints(constraints)
            .addTag(tag)
            .build()
        with (WorkManager.getInstance(this)) {
            enqueue(work)
            getWorkInfosByTagLiveData(tag).observe(this@MainActivity, Observer {
                Timber.d("work progress ${it[0].progress}")
            })
        }
    }
}