package ie.eoinahern.githubclient.util.schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainAppSchedulers : SchedulerProvider {

    override fun getIOSchecduler() = Schedulers.io()

    override fun getMainSchedulers() = AndroidSchedulers.mainThread()
}