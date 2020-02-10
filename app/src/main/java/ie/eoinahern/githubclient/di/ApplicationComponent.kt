package ie.eoinahern.githubclient.di

import dagger.Component
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.ui.login.LoginActivity


@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(login: LoginActivity)
}