/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validations;

/**
 *
 * @author Nam Phong
 */
public class DataEncryptionSHA256 {

    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }

        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append('*');
        }
        return maskedPassword.toString();
    }

}
