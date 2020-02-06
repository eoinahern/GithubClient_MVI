package ie.eoinahern.githubclient

import android.app.Application
import ie.eoinahern.githubclient.di.ApplicationComponent


class GithubApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
    }

    fun getAppComponent(): ApplicationComponent = appComponent
}