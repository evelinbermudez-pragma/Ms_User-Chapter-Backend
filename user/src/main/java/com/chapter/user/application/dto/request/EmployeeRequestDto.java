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
public class EmployeeRequestDto {
    @NotBlank(message= "The name is required")
    private String name;

    @NotBlank(message= "The lastname is required")
    private String lastName;

    @NotBlank(message= "The document is required")
    @Pattern(regexp = "\\d+", message = "The document must be only with number characters")
    private String document;

    @NotBlank(message= "The phone is required")
    @Pattern(regexp = "\\+?\\d{1,13}", message = "The phone must be only with 13 characters and can start with +")
    private String phone;

    @NotNull(message= "The birth date is required")
    private LocalDate birthDate;

    @Email
    private String email;

    @NotBlank(message= "The password is required")
    private String password;
}
