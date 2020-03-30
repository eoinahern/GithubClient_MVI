package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.data.repos.ReposRepository
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import javax.inject.Inject


class ReposProcessorHolder @Inject constructor(
    private val reposRepository: ReposRepository,
    private val schedulerProvider: SchedulerProvider
) {

}