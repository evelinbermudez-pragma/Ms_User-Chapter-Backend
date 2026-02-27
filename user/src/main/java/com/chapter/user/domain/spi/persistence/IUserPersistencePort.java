package com.chapter.user.domain.spi.persistence;
import com.chapter.user.domain.model.User;

public interface IUserPersistencePort {
    void createUser(User user);
   // User getUser(Integer id, Integer rolId);
}
