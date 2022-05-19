package com.bautoidem.chwallet.encryption

import kotlin.Throws
import com.bautoidem.chwallet.utils.AppConfig
import java.lang.Exception
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object TripleDES {

    const val CHARSET="utf-8"

    @Throws(Exception::class)
    fun encrypt(message: String): ByteArray {
        val md = MessageDigest.getInstance("md5")
        val digestOfPassword = md.digest(AppConfig.getKeyDes().toByteArray(charset(CHARSET)))
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val key: SecretKey = SecretKeySpec(keyBytes, AppConfig.getSerceyKey())
        val iv =
            IvParameterSpec(ByteArray(8))
        val cipher = Cipher.getInstance(AppConfig.getCipherKey())
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val plainTextBytes = message.toByteArray(charset(CHARSET))
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);
        return cipher.doFinal(plainTextBytes)
    }


    @Throws(Exception::class)
    fun decrypt(message: ByteArray?): String {
        val md = MessageDigest.getInstance("md5")
        val digestOfPassword = md.digest(AppConfig.getKeyDes().toByteArray(charset("utf-8")))
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val key: SecretKey = SecretKeySpec(keyBytes, AppConfig.getSerceyKey())
        val iv = IvParameterSpec(ByteArray(8))
        val decipher = Cipher.getInstance(AppConfig.getCipherKey())
        decipher.init(Cipher.DECRYPT_MODE, key, iv)

        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        val plainText = decipher.doFinal(message)
        return String(plainText, charset(CHARSET))
    }
}