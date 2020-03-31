package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.mvibase.MviViewState

data class ReposViewState(
    val error: Throwable?,
    val isProcessing: Boolean,
    val data: List<RepoItem>
) : MviViewState {

    companion object {
        fun getDefault(): ReposViewState {
            return ReposViewState(
                null,
                true,
                listOf()
            )
        }
    }
}