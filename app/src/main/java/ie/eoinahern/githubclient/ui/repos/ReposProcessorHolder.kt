package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.data.repos.ReposRepository
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalArgumentException
import javax.inject.Inject


class ReposProcessorHolder @Inject constructor(
    private val reposRepository: ReposRepository,
    private val schedulerProvider: SchedulerProvider
) {

    private val loadReposProcessor =
        ObservableTransformer<ReposAction.LoadRepos, ReposResult.LoadResposResult> { action ->
            action
                .observeOn(schedulerProvider.getIOSchecduler())
                .flatMap {
                    reposRepository.getReposList()
                }
        }


    internal val actionProcessor = ObservableTransformer<ReposAction, ReposResult> { actions ->
        Observable.merge(
            actions.ofType(ReposAction.LoadRepos::class.java).compose(loadReposProcessor),
            actions.filter { itm -> itm !is ReposAction.LoadRepos }.flatMap { _ ->
                Observable.error<ReposResult>(IllegalArgumentException("error"))
            })

    }

}