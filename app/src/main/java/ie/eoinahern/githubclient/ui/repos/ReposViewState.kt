package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.mvibase.MviViewState

data class ReposViewState(
    val error: Throwable?,
    val isProcessing: Boolean,
    val data: LiveData<PagedList<RepoItem>>?
) : MviViewState {

    companion object {
        fun getDefault(): ReposViewState {
            return ReposViewState(
                null,
                true,
                null
            )
        }
    }
}

sealed class ReposUpdatedViewState {
    data class Error(val error: Throwable?) : ReposUpdatedViewState()
    object IsProcessing : ReposUpdatedViewState()
    data class Complete(val data: PagedList<RepoItem>) : ReposUpdatedViewState()
}
