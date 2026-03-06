package com.chapter.user.domain.api;

import com.chapter.user.domain.model.User;

public interface IClientServicePort {
    void saveClient(User user);
    User getClient(Integer id);
}
