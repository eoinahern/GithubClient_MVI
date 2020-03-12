package ie.eoinahern.githubclient.data.login

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.CLIENT_SECRET
import ie.eoinahern.githubclient.util.constants.GITHUB_TOKEN_KEY
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import io.reactivex.Observable
import java.nio.charset.Charset
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val api: GithubApi, private val sharedPreferences: SharedPreferences,
    private val sharedPrefsEdit: SharedPreferences.Editor,
    private val encryptionUtil: EncryptionUtil
) {

    fun getUserToken(accessCode: String): Observable<String> =
        Observable.just(sharedPreferences.getString(GITHUB_TOKEN_KEY, ""))
            .flatMap { key ->

                Log.d("repo thread", Thread.currentThread().name)
                if (key.isNotEmpty()) {
                    Observable.just(key).map {
                        val bytes = encryptionUtil.decrypt(Base64.decode(it, Base64.NO_WRAP))
                        String(bytes, Charsets.UTF_8)
                    }
                } else {
                    api.getAuthToken(CLIENT_ID, CLIENT_SECRET, accessCode).map {
                        saveUserToken(it.accessToken)
                        it.accessToken
                    }
                }
            }

    /**
     * encrypt key and save it to shared prefs!!
     */

    private fun saveUserToken(token: String) {
        val encryptedToken = encryptionUtil.encrypt(token.toByteArray(Charsets.UTF_8))
        sharedPrefsEdit.putString(
            GITHUB_TOKEN_KEY,
            Base64.encodeToString(encryptedToken, Base64.NO_WRAP)
        ).commit()
    }
}