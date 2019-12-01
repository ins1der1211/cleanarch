package ins1der.cleanarch.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import kotlinx.coroutines.delay
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class TestWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params), KoinComponent {

    val getPlanetsUseCase: GetPlanetsUseCase by inject()

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 1L
    }

    override suspend fun doWork(): Result {
        Timber.d("$getPlanetsUseCase")
        val firstUpdate = workDataOf(Progress to 0)
        val lastUpdate = workDataOf(Progress to 100)
        setProgress(firstUpdate)
        delay(delayDuration)
        setProgress(lastUpdate)
        return Result.success()
    }

}