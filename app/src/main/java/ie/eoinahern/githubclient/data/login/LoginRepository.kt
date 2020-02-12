package ie.eoinahern.githubclient.data.login

import android.content.SharedPreferences
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.CLIENT_SECRET
import ie.eoinahern.githubclient.util.constants.GITHUB_TOKEN_KEY
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import io.reactivex.Observable
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val api: GithubApi, private val sharedPreferences: SharedPreferences,
    private val sharedPrefsEdit: SharedPreferences.Editor,
    private val encryptionUtil: EncryptionUtil
) {

    /**
     * get user key either from pref
     */

    fun getUserKey(): Observable<String> {

        val key = sharedPreferences.getString(GITHUB_TOKEN_KEY, "")

        return if (!key.isNullOrEmpty()) {
            Observable.just(key)
        } else {
            api.getAuthToken(CLIENT_ID, CLIENT_SECRET, "").map {
                it.accessToken
            }.doAfterNext { saveUserKey() }
        }
    }


    /**
     * encrypt key and save it to shared prefs!!
     */

    private fun saveUserKey() {

    }
}