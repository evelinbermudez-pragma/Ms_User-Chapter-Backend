package com.chapter.user.insfrastructure.jpa.repository;

import com.chapter.user.insfrastructure.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByEmail(String email);
    Optional<UserEntity> findUserByIdAndRole(Integer id, Integer role);
}
