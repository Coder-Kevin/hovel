package com.hovel.common.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    //AES_256_cbc pkcs7
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CHAR_ENCODING = "utf-8";

    //加密
    public static byte[] AES_cbc_encrypt(byte[] srcData, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(srcData);
    }

    //解密
    public static byte[] AES_cbc_decrypt(byte[] encData, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(encData);
    }

    public static String encryptWithKeyBase64(String data, String key) {
        try {
            byte[] originalData = (data.getBytes());
            byte[] keys = Base64.decodeBase64(key.getBytes());
            byte[] valueByte = AES_cbc_encrypt(originalData, keys, getIv(keys));
            return Base64.encodeBase64String(valueByte);
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    private static byte[] getIv(byte[] key) {
        byte[] iv = new byte[16];
        for (int i = 0; i < 16; i++) {
            iv[i] = key[i];
        }
        return iv;
    }

    public static String decryptWithKeyBase64(String data, String key) {
        try {
            byte[] originalData = Base64.decodeBase64(data.getBytes());
            byte[] keys = Base64.decodeBase64(key.getBytes());

            byte[] valueByte = AES_cbc_decrypt(originalData, keys, getIv(keys));
            return new String(valueByte, CHAR_ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String key = "2IBtBXdrqC3kCBs4gaceL7nl2nnFadQv";
        String result = "";
        String str = "test77777888";
        result = encryptWithKeyBase64(str, key);
        System.out.println("===" + result);
        result = decryptWithKeyBase64(result, key);
        System.out.println(result);

    }

}
