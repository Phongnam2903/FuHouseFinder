package Validations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataEncryptionSHA256 {

    // Phương thức băm mật khẩu bằng SHA-256
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }

        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Băm mật khẩu
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            return null; // Hoặc ném ra một ngoại lệ khác
        }
    }

    // Phương thức che giấu mật khẩu
    public static String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "";
        }

        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append('*');
        }
        return maskedPassword.toString();
    }

    public static void main(String[] args) {
        String password = "daiduong";

        // Băm mật khẩu
        String hashedPassword = DataEncryptionSHA256.hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Che giấu mật khẩu
        String maskedPassword = DataEncryptionSHA256.maskPassword(password);
        System.out.println("Masked Password: " + maskedPassword);
    }

}
