package ie.eoinahern.githubclient.di

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides

import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.util.constants.KEYSTORE_ALIAS
import ie.eoinahern.githubclient.util.constants.KEYSTORE_PROVIDER
import ie.eoinahern.githubclient.util.schedulers.MainAppSchedulers
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import java.security.KeyStore
import javax.crypto.KeyGenerator
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

    @Singleton
    @Provides
    fun getKeyGenerator(): KeyGenerator {
        return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER)
    }

    @Singleton
    @Provides
    fun getSchedulerProvider(): SchedulerProvider = MainAppSchedulers()

    @Singleton
    @Provides
    fun getKeySpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            KEYSTORE_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
    }

    @Singleton
    @Provides
    fun getKeyStore() = KeyStore.getInstance(KEYSTORE_PROVIDER)

}