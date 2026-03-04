package com.chapter.user.insfrastructure.security;

import com.chapter.user.domain.spi.IPasswordEncoderPort;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderAdapter implements IPasswordEncoderPort {

    @Override
    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
