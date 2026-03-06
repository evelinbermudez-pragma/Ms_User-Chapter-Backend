package com.chapter.user.insfrastructure.input.rest;

import com.chapter.user.application.dto.request.ClientRequestDto;
import com.chapter.user.application.dto.response.ClientResponseDto;
import com.chapter.user.application.handler.interfaces.IClientHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientHandler clientHandler;

    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        clientHandler.saveClient(clientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<ClientResponseDto> getClient(@PathVariable("id") Integer clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(clientHandler.getClient(clientId));
    }
}
