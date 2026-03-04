package com.chapter.user.domain.api;

import com.chapter.user.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {

    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
