package com.chapter.user.application.handler.interfaces;


import com.chapter.user.application.dto.request.ClientRequestDto;
import com.chapter.user.application.dto.response.ClientResponseDto;

public interface IClientHandler {
    void saveClient(ClientRequestDto user);
    ClientResponseDto getClient(Integer id);
}
