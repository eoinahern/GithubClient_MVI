package ie.eoinahern.githubclient.ui.repos

import ie.eoinahern.githubclient.mvibase.MviIntent

sealed class ReposIntent : MviIntent {

    data class LoadReposIntent(val apiKey: String) : ReposIntent()


}