package com.chapter.user.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerResponseDto {

    private Integer id;
    private String name;
    private String lastName;
    private Integer role;
    private String phone;
    private String email;
}
