package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviIntent

sealed class LoginIntent : MviIntent {

    data class EmailIntent(val email: String) : LoginIntent()
    data class PasswordIntent(val password: String) : LoginIntent()
    data class AttemptLoginIntent(
        val email: String, val password: String
    ) : LoginIntent()
}