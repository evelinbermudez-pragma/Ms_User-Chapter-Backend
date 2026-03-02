package com.chapter.user.domain.api;
import com.chapter.user.domain.model.User;

public interface IAdminServicePort {

    void saveOwner(User user);
    User getOwner(Integer id);
}
