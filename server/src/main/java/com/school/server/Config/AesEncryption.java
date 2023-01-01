package com.school.server.Config;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

public class AesEncryption {
    private final String encryptionCipher = "AES";
    private Key key;
    private Cipher cipher;
//    @Value("${aes.encryption.key}")
    private String encryptionKey = "thisIsYourSchool";


    public AesEncryption() {
        key = this.generateKey();
    }

    private Key generateKey(){
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    public String encrypt(String valueToEnc) {
        byte[] encryptedByteValue;
        try {
            cipher = Cipher.getInstance(encryptionCipher);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
            encryptedByteValue = new Base64().encode(encValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new String(encryptedByteValue);
    }

    public String decrypt(String encryptedValue){
        byte[] enctVal;
        try {
            cipher = Cipher.getInstance(encryptionCipher);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
            enctVal = cipher.doFinal(decodedBytes);
        } catch (NoSuchAlgorithmException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return new String(enctVal);
    }

}