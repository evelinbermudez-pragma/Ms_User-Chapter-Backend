package com.chapter.user.application.mapper.response;

import com.chapter.user.application.dto.response.EmployeeResponseDto;
import com.chapter.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeResponseMapper {

    EmployeeResponseDto toEmployeeResponseDto(User user);
}
