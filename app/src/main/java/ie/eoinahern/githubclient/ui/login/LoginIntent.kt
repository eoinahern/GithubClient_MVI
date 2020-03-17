package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviIntent

sealed class LoginIntent : MviIntent {
    object CheckHasKey : LoginIntent()
    data class AuthUserIntent(val code: String) : LoginIntent()
}