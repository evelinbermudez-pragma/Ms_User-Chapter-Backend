package com.chapter.user.domain.api;

import com.chapter.user.domain.model.Role;

public interface IRoleServicePort {
    Role getRoleByName(String name);
}
