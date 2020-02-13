package ie.eoinahern.githubclient.data.login

import android.content.SharedPreferences
import android.util.Base64
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

    fun getUserToken(): Observable<String> {

        val key = sharedPreferences.getString(GITHUB_TOKEN_KEY, "")

        return if (!key.isNullOrEmpty()) {
            Observable.just(key)
        } else {
            api.getAuthToken(CLIENT_ID, CLIENT_SECRET, "").map {
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
        sharedPrefsEdit.putString("token", Base64.encodeToString(encryptedToken, Base64.NO_WRAP))
            .commit()
    }
}