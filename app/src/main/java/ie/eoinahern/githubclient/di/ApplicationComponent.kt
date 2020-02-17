package ie.eoinahern.githubclient.di

import dagger.Component
import ie.eoinahern.githubclient.ui.login.LoginActivity
import ie.eoinahern.githubclient.ui.repos.ReposActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(login: LoginActivity)
    fun inject(repo: ReposActivity)
}