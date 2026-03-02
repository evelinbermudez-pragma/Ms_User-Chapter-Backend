package com.chapter.user.insfrastructure.input.rest;

import com.chapter.user.application.dto.request.EmployeeRequestDto;
import com.chapter.user.application.dto.response.EmployeeResponseDto;
import com.chapter.user.application.handler.interfaces.IOwnerHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerRestController {
    private final IOwnerHandler ownerHandler;

    @PostMapping
    public ResponseEntity<Void> savePropietario (@RequestBody @Valid EmployeeRequestDto employeeRequestDto){
        ownerHandler.saveEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<EmployeeResponseDto> getEmpleado (@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(ownerHandler.getEmployee(email));
    }
}
