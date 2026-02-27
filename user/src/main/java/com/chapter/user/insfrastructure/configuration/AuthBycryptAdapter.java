package com.chapter.user.insfrastructure.configuration;

import com.chapter.user.domain.api.IPasswordEncodedServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthBycryptAdapter implements IPasswordEncodedServicePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
