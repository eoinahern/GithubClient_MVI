package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviViewState

data class LoginViewState(
    val isProcessing: Boolean,
    val emailValid: Throwable?,
    val passwordValid: Throwable?,
    val generalFail: Throwable?,
    val isLoginComplete: Boolean
) : MviViewState {

    companion object {
        fun getInitState(): LoginViewState = LoginViewState(
            isProcessing = false,
            emailValid = null,
            passwordValid = null,
            generalFail = null,
            isLoginComplete = false
        )
    }
}