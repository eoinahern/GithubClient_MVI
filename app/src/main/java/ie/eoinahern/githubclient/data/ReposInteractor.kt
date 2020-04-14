package ie.eoinahern.githubclient.data

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import javax.inject.Inject


class ReposInteractor @Inject constructor(
    private val dataSourceFactory: ReposDataSourceFactory,
    private val config: PagedList.Config.Builder
) {

    fun getRepos(key: String): Observable<PagedList<RepoItem>> {
        dataSourceFactory.setKey(key)
        val builtConfig = config.build()
        return RxPagedListBuilder<Int, RepoItem>(
            dataSourceFactory, builtConfig
        ).buildObservable()
    }
}