package org.bootcamp4.api0009.libraries;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private Crypt() {}

    public static String md5(String cadena) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(cadena.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return cadena;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
