package com.chapter.user.insfrastructure.jpa.mapper;
import com.chapter.user.domain.model.User;
import com.chapter.user.insfrastructure.jpa.entity.UserEntity;
import com.chapter.user.insfrastructure.jpa.entity.RoleEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
     @Mapping(source = "role", target = "role", qualifiedByName = "mapIntegerToRole")
     UserEntity toEntity(User user);

     @Mapping(source = "role", target = "role", qualifiedByName = "mapRoleToInteger")
     User toModel(UserEntity userEntity);

     @Named("mapIntegerToRole")
     default RoleEntity mapIntegerToRole(Integer roleId) {
          if (roleId == null) {
               return null;
          }
          RoleEntity role = new RoleEntity();
          role.setRoleRd(roleId);
          return role;
     }

     @Named("mapRoleToInteger")
     default Integer mapRoleToInteger(RoleEntity role) {
          if (role == null) {
               return null;
          }
          return role.getRoleRd();
     }
}
