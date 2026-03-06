package com.chapter.user.domain.spi.persistence;
import com.chapter.user.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    Role getRoleById(Integer id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
