package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviResult

sealed class LoginResult : MviResult {

    sealed class EmailResult : LoginResult() {
        object Processing : EmailResult()
        data class Success(val email: String) : EmailResult()
        data class Failure(val error: Throwable) : EmailResult()
    }

    sealed class PasswordResult : LoginResult() {
        object Processing : PasswordResult()
        data class Success(val email: String) : PasswordResult()
        data class Failure(val error: Throwable) : PasswordResult()
    }

    sealed class LoginAttemptResult : LoginResult() {
        object Processing : LoginAttemptResult()
        object Success : LoginAttemptResult()
        data class Failure(val error: Throwable) : LoginAttemptResult()
    }
}