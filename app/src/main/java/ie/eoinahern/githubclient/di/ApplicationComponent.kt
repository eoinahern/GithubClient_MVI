package ie.eoinahern.githubclient.di

import dagger.Component
import ie.eoinahern.githubclient.ui.login.LoginActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(login: LoginActivity)
}