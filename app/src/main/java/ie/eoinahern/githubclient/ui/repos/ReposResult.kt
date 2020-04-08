package ie.eoinahern.githubclient.ui.repos

import androidx.paging.PagedList
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.mvibase.MviResult


sealed class ReposResult : MviResult {

    sealed class LoadResposResult : ReposResult() {
        object Processing : LoadResposResult()
        data class LoadError(val err: Throwable) : LoadResposResult()
        data class Success(val data: PagedList<RepoItem>) : LoadResposResult()
    }

}