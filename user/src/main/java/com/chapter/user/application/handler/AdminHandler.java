package com.chapter.user.application.handler;

import com.chapter.user.application.dto.request.OwnerRequestDto;
import com.chapter.user.application.dto.response.OwnerResponseDto;
import com.chapter.user.application.handler.interfaces.IAdminHandler;
import com.chapter.user.application.mapper.request.OwnerRequestMapper;
import com.chapter.user.application.mapper.response.OwnerResponseMapper;
import com.chapter.user.domain.api.IAdminServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Component
@RequiredArgsConstructor
//@Transactional TODO: INVESTIGAR SI SE DEBE PONER TRANSACTIONAL EN EL HANDLER O EN EL SERVICE
public class AdminHandler implements IAdminHandler{

    private final IAdminServicePort adminServicePort;
    private final OwnerRequestMapper ownerRequestMapper;
    private final OwnerResponseMapper ownerResponseMapper;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        adminServicePort.saveOwner(ownerRequestMapper.toUser(ownerRequestDto));
    }
    @Override
    public OwnerResponseDto getOwner(Integer id) {
        return ownerResponseMapper.toOwnerResponseDto(adminServicePort.getOwner(id));
    }
}
