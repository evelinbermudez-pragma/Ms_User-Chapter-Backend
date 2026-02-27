package com.chapter.user.insfrastructure.input.rest;

import com.chapter.user.application.dto.request.UserRequestDto;
import com.chapter.user.application.handler.interfaces.IAdminHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final IAdminHandler adminHandler;

    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@RequestBody UserRequestDto userRequestDto) {
        adminHandler.saveOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
