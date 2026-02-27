package com.chapter.user.application.mapper.request;

import com.chapter.user.application.dto.request.UserRequestDto;
import com.chapter.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {
    User toModel(UserRequestDto userRequestDto);
}
