package com.chapter.user.application.handler.interfaces;

import com.chapter.user.application.dto.request.EmployeeRequestDto;
import com.chapter.user.application.dto.response.EmployeeResponseDto;

public interface IOwnerHandler {

    void saveEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployee(String email);
}
