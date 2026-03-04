package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IClientServicePort;
import com.chapter.user.domain.model.User;
import com.chapter.user.domain.spi.IPasswordEncoderPort;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;

public class ClientUseCase implements IClientServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRolePersistencePort rolePersistencePort;

    public ClientUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.rolePersistencePort = rolePersistencePort;
    }
    @Override
    public void saveClient(User user){
        user.setPassword(passwordEncoderPort.encryptPassword(user.getPassword()));
        user.setRole(rolePersistencePort.getRoleByName("CLIENT").getRoleId());
        userPersistencePort.createUser(user);
    }
    @Override
    public User getClient(Integer id){
        return userPersistencePort.getUser(id, rolePersistencePort.getRoleByName("CLIENT").getRoleId());
    }
}
