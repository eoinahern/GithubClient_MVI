package ie.eoinahern.githubclient.util.encrypt

import android.security.keystore.KeyGenParameterSpec
import javax.crypto.KeyGenerator
import javax.inject.Inject

class EncryptionUtil @Inject constructor(
    private val keyGenerator: KeyGenerator,
    private val keyGenParameterSpec: KeyGenParameterSpec
) {

    init {
        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }


    fun encrypt() {

    }


    fun decrypt() {

    }
}