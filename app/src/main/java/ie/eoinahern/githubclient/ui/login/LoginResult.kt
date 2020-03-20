package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviResult

sealed class LoginResult : MviResult {

    sealed class LoginAttemptResult : LoginResult() {
        object Processing : LoginAttemptResult()
        data class Success(val key: String) : LoginAttemptResult()
        data class Failure(val error: Throwable) : LoginAttemptResult()
    }

    sealed class CheckHasKeyResult : LoginResult() {
        object Processing : CheckHasKeyResult()
        data class Success(val key: String) : CheckHasKeyResult()
        object Failure : CheckHasKeyResult()
    }
}