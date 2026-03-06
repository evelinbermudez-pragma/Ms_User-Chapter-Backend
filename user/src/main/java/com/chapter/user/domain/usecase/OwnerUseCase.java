package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IOwnerServicePort;
import com.chapter.user.domain.spi.IPasswordEncoderPort;
import com.chapter.user.domain.model.User;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;


public class OwnerUseCase implements IOwnerServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncoderPort passwordEncodedServicePort;

    public OwnerUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncodedServicePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncodedServicePort = passwordEncodedServicePort;
    }
    @Override
    public void saveEmployee(User user) {
        user.setRole(rolePersistencePort.getRoleByName("EMPLOYEE").getRoleId());
        user.setPassword(passwordEncodedServicePort.encryptPassword(user.getPassword()));
        userPersistencePort.createUser(user);

    }
    @Override
    public User getEmployee(String email) {
        return userPersistencePort.getEmployee(email);
    }

}
