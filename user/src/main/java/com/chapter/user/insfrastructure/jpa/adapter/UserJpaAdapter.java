package com.chapter.user.insfrastructure.jpa.adapter;

import com.chapter.user.domain.model.User;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;
import com.chapter.user.insfrastructure.jpa.entity.UserEntity;
import com.chapter.user.insfrastructure.jpa.exception.UserNotFoundException;
import com.chapter.user.insfrastructure.jpa.mapper.UserEntityMapper;
import com.chapter.user.insfrastructure.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
        private final IUserRepository userRepository;
        private final UserEntityMapper userEntityMapper;

        @Override
        public void createUser(User user) {
                userRepository.save(userEntityMapper.toUserEntity(user));
        }

        @Override
        public User getUser(Integer id, Integer rolId) {
                Optional<UserEntity> userEntity = userRepository.findUserByIdAndRole(id, rolId);
                if (userEntity.isEmpty()) {
                        throw new UserNotFoundException("User not found with id: " + id);
                }
                return userEntityMapper.toUser(userEntity.get());
        }
        @Override
        public User getEmployee(String email) {
                Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail(email);
                if(userEntity.isEmpty()){
                                throw new UserNotFoundException("Employee doesn´t exist with email: " + email);
                }
                return userEntityMapper.toUser(userEntity.get());
        }
        }

