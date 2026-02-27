package com.chapter.user.insfrastructure.jpa.mapper;
import com.chapter.user.domain.model.Role;
import com.chapter.user.insfrastructure.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
        RoleEntity toEntity(Role role);
        Role toModel(RoleEntity roleEntity);
}
