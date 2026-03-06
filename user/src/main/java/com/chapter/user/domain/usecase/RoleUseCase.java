package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IRoleServicePort;
import com.chapter.user.domain.model.Role;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort persistencePort;

    public RoleUseCase(IRolePersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public List<Role> getAllRoles() {
        return persistencePort.getAllRoles();
    }

    @Override
    public Role getRoleByName(String rolName) {
        return persistencePort.getRoleByName(rolName);
    }
}
