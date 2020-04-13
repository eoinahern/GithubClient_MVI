package ie.eoinahern.githubclient.data

import androidx.paging.PagedList
import ie.eoinahern.githubclient.ui.repos.ReposUpdatedViewState
import io.reactivex.Observable
import javax.inject.Inject


class ReposInteractor @Inject constructor(
    private val dataSourceFactory: ReposDataSourceFactory,
    private val config: PagedList.Config.Builder
) {

    fun getRepos(): Observable<ReposUpdatedViewState> {

    }
}