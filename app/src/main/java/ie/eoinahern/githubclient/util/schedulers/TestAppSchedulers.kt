package ie.eoinahern.githubclient.util.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestAppSchedulers : SchedulerProvider {

    override fun getIOSchecduler(): Scheduler = Schedulers.trampoline()

    override fun getMainSchedulers() = Schedulers.trampoline()
}