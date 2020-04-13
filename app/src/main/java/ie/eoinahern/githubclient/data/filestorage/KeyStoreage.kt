package ie.eoinahern.githubclient.data.filestorage

import android.content.SharedPreferences
import android.util.Base64
import ie.eoinahern.githubclient.util.constants.GITHUB_TOKEN_KEY
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import io.reactivex.Observable
import java.lang.IllegalArgumentException
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * decouple my file storage of key from the api call
 *
 */

class KeyStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sharedPrefsEdit: SharedPreferences.Editor,
    private val encryptionUtil: EncryptionUtil
) {

    fun saveUserToken(token: String) {
        val encryptedToken = encryptionUtil.encrypt(token.toByteArray(Charset.defaultCharset()))
        val encryptedString = Base64.encodeToString(encryptedToken, Base64.NO_WRAP)
        sharedPrefsEdit.putString(
            GITHUB_TOKEN_KEY,
            encryptedString
        ).commit()
    }

    private fun getKeyFromPrefs(): String {
        val encryptedKey = sharedPreferences.getString(GITHUB_TOKEN_KEY, "")

        if (encryptedKey.isNullOrEmpty()) {
            throw IllegalArgumentException("empty key")
        }

        val decoded = Base64.decode(encryptedKey, Base64.NO_WRAP)
        val bytes = encryptionUtil.decrypt(decoded)
        return String(bytes, Charsets.UTF_8)
    }

    fun checkHasLocalToken(): Observable<String> {
        return Observable.just(getKeyFromPrefs())
    }
}