/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validations;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Tuan
 */
public class DataEncryptionSHA256 {
    /**
     * data encryption with SHA-256
     * 
     * @param password password user
     * @return a code after transfer 
     */
    public static String hashPassword(String password) {
        try {
            // Tạo instance của MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Chuyển chuỗi mật khẩu thành mảng byte
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Chuyển mảng byte thành chuỗi hex
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * transfer type byte to hex
     * 
     * @param hash 
     * @return 
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
