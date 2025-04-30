package com.registrations.users.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static boolean matches(String password, String encodedPassword) {
        String encoded = encode(encodedPassword);
        return encoded.equals(password);
    }
}
