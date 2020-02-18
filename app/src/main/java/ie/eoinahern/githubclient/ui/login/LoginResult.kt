package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviResult

sealed class LoginResult : MviResult {

    sealed class LoginAttemptResult : LoginResult() {
        object Processing : LoginAttemptResult()
        object Success : LoginAttemptResult()
        data class Failure(val error: Throwable) : LoginAttemptResult()
    }
}