package ie.eoinahern.githubclient

import android.app.Application
import ie.eoinahern.githubclient.di.ApplicationComponent
import ie.eoinahern.githubclient.di.ApplicationModule
import ie.eoinahern.githubclient.di.DaggerApplicationComponent


class GithubApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
    }

    fun getAppComponent(): ApplicationComponent = appComponent
}