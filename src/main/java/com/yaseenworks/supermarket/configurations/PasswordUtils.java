package com.yaseenworks.supermarket.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class PasswordUtils {
    private final PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

     public String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:'\\\",.<>?/\"";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }
        return password.toString();
    }

    public String generateEncodedPassword() {
        return encodePassword(generateRandomPassword());
    }
}
