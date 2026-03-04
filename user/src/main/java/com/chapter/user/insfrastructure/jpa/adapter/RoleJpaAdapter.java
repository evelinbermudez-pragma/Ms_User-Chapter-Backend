package com.chapter.user.insfrastructure.jpa.adapter;

import com.chapter.user.domain.model.Role;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.insfrastructure.jpa.mapper.RoleEntityMapper;
import com.chapter.user.insfrastructure.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(roleEntityMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id)
                .map(roleEntityMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Role not found: " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}
