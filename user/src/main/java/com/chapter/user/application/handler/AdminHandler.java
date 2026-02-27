package com.chapter.user.application.handler;

import com.chapter.user.application.dto.request.UserRequestDto;
import com.chapter.user.application.handler.interfaces.IAdminHandler;
import com.chapter.user.application.mapper.request.UserRequestMapper;
import com.chapter.user.domain.api.IAdminServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminHandler implements IAdminHandler{

    private final IAdminServicePort adminServicePort;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        adminServicePort.saveOwner(userRequestMapper.toModel(userRequestDto));
    }
}
