package com.huiju.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtils {
    public static SecretKeySpec skey;
    public static Cipher cipher;

    static {
        try {
            String secretKey = NeuUtils.getProperty("secretKey");// 密钥
            skey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            throw new RuntimeException("init.static异常：" + e);
        }
    }

    // 加密
    public static String getEncString(String msg) {
        byte[] crypted = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(msg.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException("getEncString.static异常：" + e);
        }
        return new String(Base64.encodeBase64(crypted));
    }

    // 解密
    public static String getDesString(String msg) {
        byte[] output = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(msg));
        } catch (Exception e) {
            throw new RuntimeException("getDesString.static异常：" + e);
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String str = "中华人民共和国万岁";
        // 加密   
        String encStr = DESUtils.getEncString(str);
        System.out.println("密文：" + encStr);
        // 解密   
        String desStr = DESUtils.getDesString(encStr);
        System.out.println("明文：" + desStr);
    }

}