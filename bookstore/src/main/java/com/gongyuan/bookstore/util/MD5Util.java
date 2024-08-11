package com.gongyuan.bookstore.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: gongyuan
 * @date: 2024/8/10 15:27
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MD5Util {

    private static final String MD5 = "MD5";

    /**
     * 生成md5
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateMD5(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
