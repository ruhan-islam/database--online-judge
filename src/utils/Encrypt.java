package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public static String encrypt(String password) {
        String algorithm = "SHA";

        byte[] plainText = password.getBytes();


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(md == null) {
            return password;
        }

        md.reset();
        md.update(plainText);
        byte[] encodedPassword = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : encodedPassword) {
            if ((b & 0xff) < 0x10) {
                sb.append("0");
            }

            sb.append(Long.toString(b & 0xff, 16));
        }

        return sb.toString();
    }
}
