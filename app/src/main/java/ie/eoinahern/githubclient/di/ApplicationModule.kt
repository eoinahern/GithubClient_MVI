package ie.eoinahern.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

import ie.eoinahern.githubclient.GithubApp

class ApplicationModule(private val app: GithubApp) {

    fun getContext(): Context = app.applicationContext

    fun getPrefs(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

}