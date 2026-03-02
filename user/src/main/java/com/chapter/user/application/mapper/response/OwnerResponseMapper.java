package com.chapter.user.application.mapper.response;

import com.chapter.user.application.dto.response.OwnerResponseDto;
import com.chapter.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerResponseMapper {

    OwnerResponseDto toOwnerResponseDto (User user);
}
