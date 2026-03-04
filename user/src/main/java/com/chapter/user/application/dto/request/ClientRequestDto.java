package com.chapter.user.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientRequestDto {
    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The last name is required")
    private String lastName;

    private LocalDate birthDate;

    @NotNull
    private Integer document;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "The phone number must be between 1 and 12 digits, and can optionally start with a '+'")
    private String phone;

    @NotBlank(message = "The password is required")
    private String password;
}
