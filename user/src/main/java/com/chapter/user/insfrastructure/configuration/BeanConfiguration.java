package com.chapter.user.insfrastructure.configuration;

import com.chapter.user.domain.api.IAdminServicePort;
import com.chapter.user.domain.api.IClientServicePort;
import com.chapter.user.domain.api.IOwnerServicePort;
import com.chapter.user.domain.spi.IPasswordEncoderPort;
import com.chapter.user.domain.spi.persistence.IRolePersistencePort;
import com.chapter.user.domain.spi.persistence.IUserPersistencePort;
import com.chapter.user.domain.usecase.AdminUseCase;
import com.chapter.user.domain.usecase.ClientUseCase;
import com.chapter.user.domain.usecase.OwnerUseCase;
import com.chapter.user.insfrastructure.jpa.adapter.RoleJpaAdapter;
import com.chapter.user.insfrastructure.jpa.adapter.UserJpaAdapter;
import com.chapter.user.insfrastructure.jpa.mapper.RoleEntityMapper;
import com.chapter.user.insfrastructure.jpa.mapper.UserEntityMapper;
import com.chapter.user.insfrastructure.jpa.repository.IRoleRepository;
import com.chapter.user.insfrastructure.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IPasswordEncoderPort passwordEncodedServicePort(){
        return new AuthBycryptAdapter(passwordEncoder());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IAdminServicePort adminServicePort(){
        return new AdminUseCase(userPersistencePort(), passwordEncodedServicePort(), rolePersistencePort());
    }
    @Bean
    public IOwnerServicePort ownerServicePort(){
        return new OwnerUseCase(userPersistencePort(), passwordEncodedServicePort(), rolePersistencePort());
    }
    @Bean
    public IClientServicePort clientUseCase(){
        return new ClientUseCase(userPersistencePort(), passwordEncodedServicePort(), rolePersistencePort());
    }
}
