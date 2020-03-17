package ie.eoinahern.githubclient.util.encrypt

import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import ie.eoinahern.githubclient.util.constants.KEYSTORE_ALIAS
import ie.eoinahern.githubclient.util.constants.TRANSFORMATION_AES
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

private const val IV_KEY = "iv"


class EncryptionUtil @Inject constructor(
    private val keyGenerator: KeyGenerator,
    private val keyGenParameterSpec: KeyGenParameterSpec,
    private val keyStore: KeyStore,
    private val prefs: SharedPreferences,
    private val editPrefs: SharedPreferences.Editor
) {

    init {
        keyStore.load(null)
        if (!keyStore.containsAlias(KEYSTORE_ALIAS)) {
            keyGenerator.init(keyGenParameterSpec)
            keyGenerator.generateKey()
        }
    }

    @Synchronized
    fun encrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION_AES)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
        val ivBytes = cipher.iv
        val encryptedIV = android.util.Base64.encodeToString(
            ivBytes,
            android.util.Base64.NO_WRAP
        )
        editPrefs.putString(IV_KEY, encryptedIV).commit()
        return cipher.doFinal(data)
    }

    @Synchronized
    fun decrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION_AES)
        val encryptedIV = prefs.getString(IV_KEY, "")
        val iv = android.util.Base64.decode(encryptedIV, android.util.Base64.NO_WRAP)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec)
        return cipher.doFinal(data)
    }

    private fun getSecretKey(): SecretKey {
        val secretKeyEntry = keyStore.getEntry(KEYSTORE_ALIAS, null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }
}
