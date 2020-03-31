package ie.eoinahern.githubclient.ui.repos

import android.util.Log
import ie.eoinahern.githubclient.data.repos.ReposRepository
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.ObservableTransformer
import javax.inject.Inject


class ReposProcessorHolder @Inject constructor(
    private val reposRepository: ReposRepository,
    private val schedulerProvider: SchedulerProvider
) {

    private val loadReposProcessor =
        ObservableTransformer<ReposAction.LoadRepos, ReposResult.LoadResposResult> { actions ->
            actions.observeOn(schedulerProvider.getIOSchecduler())
                .flatMap { action ->
                    reposRepository.getReposList(action.apiKey)
                }.map { data ->
                    ReposResult.LoadResposResult.Success(data)
                }
                .cast(ReposResult.LoadResposResult::class.java)
                .observeOn(schedulerProvider.getMainSchedulers())
                .onErrorReturn { throwable ->
                    Log.d("error", throwable.message ?: "error")
                    ReposResult.LoadResposResult.LoadError(throwable)
                }
        }

    internal val actionProcessor = ObservableTransformer<ReposAction, ReposResult> { actions ->
        actions.ofType(ReposAction.LoadRepos::class.java).compose(loadReposProcessor)
    }

}