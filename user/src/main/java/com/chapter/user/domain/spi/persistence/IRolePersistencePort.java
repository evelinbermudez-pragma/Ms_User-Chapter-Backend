package com.chapter.user.domain.spi.persistence;
import com.chapter.user.domain.model.Role;

public interface IRolePersistencePort {
   // Role getRoleById(Integer id);
    Role getRoleByName(String name);
  //  List<Role> getRoles();
}
