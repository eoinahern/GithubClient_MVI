package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.mvibase.MviViewState

data class ReposViewState(
    val name: String,
    val isProcessing: Boolean,
    val data: String
) : MviViewState {

    companion object {
        fun getDefault(): ReposViewState {
            return ReposViewState(
                "hello", false,
                ""
            )
        }
    }
}