package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.mvibase.MviAction


sealed class LoginAction : MviAction {

    data class AuthUserAction(val code: String) : LoginAction()
}