package com.chapter.user.application.handler;

import com.chapter.user.application.dto.request.ClientRequestDto;
import com.chapter.user.application.dto.response.ClientResponseDto;
import com.chapter.user.application.handler.interfaces.IClientHandler;
import com.chapter.user.application.mapper.request.ClientRequestMapper;
import com.chapter.user.application.mapper.response.ClientResponseMapper;
import com.chapter.user.domain.api.IClientServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class ClientHandler implements IClientHandler {
    private final IClientServicePort clienteServicePort;
    private final ClientRequestMapper clienteRequestMapper;
    private final ClientResponseMapper clienteResponseMapper;

    @Override
    public void saveClient(ClientRequestDto clientRequestDto) {
        clienteServicePort.saveClient(clienteRequestMapper.toUser(clientRequestDto));
    }

    @Override
    public ClientResponseDto getClient(Integer id){
        return clienteResponseMapper.toClientResponseDto(clienteServicePort.getClient(id));
    }
}
