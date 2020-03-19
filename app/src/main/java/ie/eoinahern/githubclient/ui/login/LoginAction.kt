package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviAction


sealed class LoginAction : MviAction {
    object CheckHasKeyAction : LoginAction()
    data class AuthUserAction(val code: String) : LoginAction()
}