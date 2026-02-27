package com.chapter.user.domain.usecase;

import com.chapter.user.domain.api.IPasswordEncodedServicePort;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;
import com.chapter.user.domain.api.IAdminServicePort;
import com.chapter.user.domain.exception.IsOlderUserException;
import com.chapter.user.domain.model.User;
import com.chapter.user.domain.validate.UserValidate;

import java.time.LocalDate;
import java.time.ZoneId;

public class AdminUseCase implements IAdminServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncodedServicePort passwordEncodedServicePort;

    public AdminUseCase(IUserPersistencePort userPersistencePort, IPasswordEncodedServicePort passwordEncodedServicePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncodedServicePort = passwordEncodedServicePort;
    }

    @Override
    public void saveOwner(User user){
        LocalDate localDate = user.getBirthDate()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

        if(!UserValidate.isOlder(localDate.toString())){
            throw new IsOlderUserException();
        }
        user.setRole(rolePersistencePort.getRoleByName("OWNER").getRoleId());
        user.setPassword(passwordEncodedServicePort.encryptPassword(user.getPassword()));
        userPersistencePort.createUser(user);
    }
    /*
    @Override
    public User getOwner(Integer id){
        return userPersistencePort.getUser(id, rolePersistencePort.getRoleByName("OWNER").getRoleId());
    }*/
}
