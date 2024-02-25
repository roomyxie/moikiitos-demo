package com.moikiitos.util;

import java.security.MessageDigest;

public class SecurityUtil {
    /*
     *function description
     * @author xiekuan
     * @Description   md5 hash
     * @date 02/25/24
     * @param:
     * source： origin input string for input
     * hashIterations
     * @return:  java.lang.String
     *
     */
    public static String md5Hash(String source, int hashIterations) {

        return md5Hash(source, null, hashIterations);

    }

    /*
     *function description
     * @author xiekuan
     * @Description   md5 ahsh
     * @date 02/25/24
     * @param:
     * source： origin input string for input
     * salt: salt
     * hashIterations
     * @return:  java.lang.String
     *
     */
    public static String md5Hash(String source, String salt, int hashIterations) {

        StringBuilder sb = new StringBuilder();
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            for (int i = 0; i < hashIterations; i++) {
                md5.update(source.getBytes("utf8"));
                if (salt != null) {
                    md5.update(salt.getBytes("utf8"));
                }
            }

            byte[] result = md5.digest();

            for (byte b : result) {
                sb.append(String.format("%02X", b));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public static String shaHash(String source, int hashIterations) {

        return md5Hash(source, null, hashIterations);

    }

    public static String shaHash(String source, String salt, int hashIterations) {

        StringBuilder sb = new StringBuilder();
        try {

            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            for (int i = 0; i < hashIterations; i++) {
                sha1.update(source.getBytes("utf8"));
                if (salt != null) {
                    sha1.update(salt.getBytes("utf8"));
                }
            }

            byte[] result = sha1.digest();

            for (byte b : result) {
                sb.append(String.format("%02X", b));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
}
