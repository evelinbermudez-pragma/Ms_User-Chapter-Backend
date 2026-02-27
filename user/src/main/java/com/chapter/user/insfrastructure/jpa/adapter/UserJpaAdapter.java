package com.chapter.user.insfrastructure.jpa.adapter;

import com.chapter.user.domain.model.User;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;
import com.chapter.user.insfrastructure.jpa.mapper.UserEntityMapper;
import com.chapter.user.insfrastructure.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
        private final IUserRepository userRepository;
        private final UserEntityMapper userEntityMapper;

        @Override
        public void createUser(User user) {
            userRepository.save(userEntityMapper.toEntity(user));
        }
}
