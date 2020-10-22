

import com.example.luxurykotlin.utils.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by OpenYourEyes on 1/13/2020
 */
class AppSchedulerProvider : SchedulerProvider {
    override val computation: Scheduler
        get() = Schedulers.computation()
    override val io: Scheduler
        get() = Schedulers.io()
    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()
}