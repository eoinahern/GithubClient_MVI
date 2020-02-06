package ie.eoinahern.githubclient.di

import ie.eoinahern.githubclient.GithubApp

interface ApplicationComponent {

    fun inject(app: GithubApp)
}