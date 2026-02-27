package com.chapter.user.application.handler.interfaces;

import com.chapter.user.application.dto.request.UserRequestDto;

public interface IAdminHandler {
    void saveOwner(UserRequestDto userRequestDto);
    
}
