package com.chapter.user.insfrastructure.input.rest;
import com.chapter.user.application.dto.request.AuthRequestDto;
import com.chapter.user.application.dto.response.AuthResponseDto;
import com.chapter.user.application.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final UserDetailsImpl userDetailsImpl;

    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthRequestDto authRequestDto) {
        try {
                log.info("Intento de login para: {}", authRequestDto.getEmail());
            AuthResponseDto response = userDetailsImpl.loginUser(authRequestDto);
            log.info("Login exitoso para: {}", authRequestDto.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("Error en login: ", e);
            throw e;
        }
    }
}
