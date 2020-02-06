package ie.eoinahern.githubclient.util

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun getIOSchecduler(): Scheduler

    fun getMainSchedulers(): Scheduler
}