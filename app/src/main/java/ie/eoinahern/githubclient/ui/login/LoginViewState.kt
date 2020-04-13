package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviViewState

data class LoginViewState(
    val isProcessing: Boolean,
    val generalFail: Throwable?,
    val loginComplete: Boolean,
    val key: String?,
    val visibleLoginButton: Boolean
) : MviViewState {

    companion object {
        fun getInitState(): LoginViewState = LoginViewState(
            isProcessing = false,
            generalFail = null,
            key = null,
            loginComplete = false,
            visibleLoginButton = false
        )
    }
}

sealed class LoginScreenViewState {
    object IntermediateState : LoginScreenViewState()
    data class CompleteState(val key: String) : LoginScreenViewState()
    data class FailureState(val throwable: Throwable?) : LoginScreenViewState()
    object ProgressState : LoginScreenViewState()
}
