package ie.eoinahern.githubclient.di

import dagger.Component
import ie.eoinahern.githubclient.GithubApp


@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(app: GithubApp)
}