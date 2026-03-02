package com.chapter.user.domain.api;
import com.chapter.user.domain.model.User;

public interface IOwnerServicePort {

    void saveEmployee(User user);
    User getEmployee(String email);
}
