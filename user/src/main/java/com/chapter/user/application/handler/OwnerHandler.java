package com.chapter.user.application.handler;

import com.chapter.user.application.dto.request.EmployeeRequestDto;
import com.chapter.user.application.dto.response.EmployeeResponseDto;
import com.chapter.user.application.handler.interfaces.IOwnerHandler;
import com.chapter.user.application.mapper.request.EmployeeRequestMapper;
import com.chapter.user.application.mapper.response.EmployeeResponseMapper;
import com.chapter.user.domain.api.IOwnerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {
    private final IOwnerServicePort ownerServicePort;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;

    @Override
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
            ownerServicePort.saveEmployee(employeeRequestMapper.toUser(employeeRequestDto));
    }
    @Override
    public EmployeeResponseDto getEmployee(String email){
        return employeeResponseMapper.toEmployeeResponseDto(ownerServicePort.getEmployee(email));
    }
}
