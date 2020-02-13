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

class EncryptionUtil @Inject constructor(
    private val keyGenerator: KeyGenerator,
    private val keyGenParameterSpec: KeyGenParameterSpec,
    private val keyStore: KeyStore,
    private val prefs: SharedPreferences,
    private val editPrefs: SharedPreferences.Editor
) {

    private lateinit var ivBytes: ByteArray

    init {
        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
        keyStore.load(null)
    }

    fun encrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION_AES)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
        ivBytes = cipher.iv
        editPrefs.putString(
            "iv",
            android.util.Base64.encodeToString(ivBytes, android.util.Base64.NO_WRAP)
        ).commit()
        return cipher.doFinal(data)
    }


    fun decrypt(data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION_AES)
        val strIV = prefs.getString("iv", "")
        val iv = android.util.Base64.decode(strIV, android.util.Base64.NO_WRAP)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec)
        return cipher.doFinal(data)
    }


    private fun getSecretKey(): SecretKey {
        val secretKeyEntry =
            keyStore.getEntry(KEYSTORE_ALIAS, null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }
}