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
    @Value("${aes.encryption.key}")
    private String encryptionKey = "thisIsYourSchool";

//    public static void main(String args[]) throws Exception {
//        AesEncryption aes = new AesEncryption();
//        System.out.println(aes.encryptionKey);
//        Key key = aes.generateKey();
//        String encriptValue = aes.encrypt("YOUR_SECRETE_KEY",key);
//        System.out.println(encriptValue);
//        String d = aes.decrypt(encriptValue,key);
//        System.out.println(d);
//
//    }

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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return new String(enctVal);
    }

}