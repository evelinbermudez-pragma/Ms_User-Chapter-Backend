package com.chapter.user.application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String message;

    public AuthResponseDto(String token){
        this.token = token;
        this.message = "Authentication successful";
    }
}
