package ie.eoinahern.githubclient.ui.repos

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.data.repos.ReposRepository
import ie.eoinahern.githubclient.di.PagingModule
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject


/*class ReposProcessorHolder @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val dataSourceFactory: PagingModule.MyFactory,
    private val config: PagedList.Config.Builder
) {

    private val loadReposProcessor =
        ObservableTransformer<ReposAction.LoadRepos, ReposResult.LoadResposResult> { actions ->
            actions.observeOn(schedulerProvider.getIOSchecduler())
                .map { action ->

                    dataSourceFactory.setKey(action.apiKey)
                    val builtConfig = config.build()
                    LivePagedListBuilder<Int, RepoItem>(
                        dataSourceFactory, builtConfig
                    ).build()
                }
                .map { list -> ReposResult.LoadResposResult.Success(list) }
                .cast(ReposResult.LoadResposResult::class.java)
                .observeOn(schedulerProvider.getMainSchedulers())
                .onErrorReturn { throwable ->
                    Log.d("error", throwable.message ?: "error")
                    ReposResult.LoadResposResult.LoadError(throwable)
                }.distinctUntilChanged()
        }

    internal val actionProcessor = ObservableTransformer<ReposAction, ReposResult> { actions ->
        actions.ofType(ReposAction.LoadRepos::class.java).compose(loadReposProcessor)
    }

}*/