package ie.eoinahern.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides

import ie.eoinahern.githubclient.GithubApp
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: GithubApp) {

    @Singleton
    @Provides
    fun getContext(): Context = app.applicationContext

    @Singleton
    @Provides
    fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun getSharedPresEdit(prefs: SharedPreferences): SharedPreferences.Editor {
        return prefs.edit()
    }

}