package ie.eoinahern.githubclient

import android.app.Application


class GithubApp : Application() {

    override fun onCreate() {
        super.onCreate()
        println("hello")
    }
}