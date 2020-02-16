package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviViewState

data class LoginViewState(
    val isProcessing: Boolean,
    val generalFail: Throwable?,
    val loginComplete: Boolean
) : MviViewState {

    companion object {
        fun getInitState(): LoginViewState = LoginViewState(
            isProcessing = false,
            generalFail = null,
            loginComplete = false
        )
    }
}