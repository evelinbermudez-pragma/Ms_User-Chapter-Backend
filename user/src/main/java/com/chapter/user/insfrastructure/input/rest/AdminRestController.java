package com.chapter.user.insfrastructure.input.rest;

import com.chapter.user.application.dto.request.OwnerRequestDto;
import com.chapter.user.application.dto.request.UserRequestDto;
import com.chapter.user.application.dto.response.OwnerResponseDto;
import com.chapter.user.application.handler.interfaces.IAdminHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final IAdminHandler adminHandler;

    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@RequestBody @Valid OwnerRequestDto ownerRequestDto) {
        adminHandler.saveOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerResponseDto> getOwner(@PathVariable("id") Integer id){
        try {
            log.info("Obteniendo propietario con id: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(adminHandler.getOwner(id));
        } catch (Exception e) {
            log.error("Error al obtener propietario: ", e);
            throw e;
        }
    }
}
