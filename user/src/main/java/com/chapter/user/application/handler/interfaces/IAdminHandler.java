package com.chapter.user.application.handler.interfaces;

import com.chapter.user.application.dto.request.OwnerRequestDto;
import com.chapter.user.application.dto.request.UserRequestDto;
import com.chapter.user.application.dto.response.OwnerResponseDto;

public interface IAdminHandler {
    void saveOwner(OwnerRequestDto ownerRequestDto);
    OwnerResponseDto getOwner(Integer ownerId);
    
}
