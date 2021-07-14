package eu.javimar.groceryshopping.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import eu.javimar.usecases.ReloadGroceriesBackground
import retrofit2.HttpException

@HiltWorker
class RefreshNetworkGroceries @AssistedInject constructor(
        @Assisted appContext: Context,
        @Assisted workerParams: WorkerParameters,
        private val reloadGroceriesBackground: ReloadGroceriesBackground) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return try {
            reloadGroceriesBackground.invoke()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
    companion object {
        const val WORK_NAME = "RefreshGroceriesWork"
    }
}