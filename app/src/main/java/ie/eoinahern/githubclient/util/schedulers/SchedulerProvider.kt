package ie.eoinahern.githubclient.util.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun getIOSchecduler(): Scheduler

    fun getMainSchedulers(): Scheduler
}