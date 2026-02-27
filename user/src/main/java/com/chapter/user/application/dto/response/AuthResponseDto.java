package com.chapter.user.application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {
    private String message;

    public AuthResponseDto(){
        this.message = "Authentication successful";
    }
}
