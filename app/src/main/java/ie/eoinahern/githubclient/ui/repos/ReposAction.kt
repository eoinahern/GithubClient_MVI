package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.mvibase.MviAction


sealed class ReposAction : MviAction {
    data class LoginAction(val apiKey: String) : ReposAction()
}