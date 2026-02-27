package com.chapter.user.application.security;
import java.util.ArrayList;
import java.util.List;

import com.chapter.user.application.dto.request.AuthRequestDto;
import com.chapter.user.application.dto.response.AuthResponseDto;
import com.chapter.user.insfrastructure.jpa.entity.UserEntity;
import com.chapter.user.insfrastructure.jpa.repository.IUserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsImpl implements UserDetailsService{
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public UserDetailsImpl(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, IUserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user " + username + " no exist."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
        return new CustomUserDetails(
                userEntity.getId().longValue(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                authorityList
        );
    }

    public AuthResponseDto loginUser(AuthRequestDto authRequestDto) {
        try {
            log.info("Buscando usuario con correo: {}", authRequestDto.getEmail());

            UserEntity userEntity = userRepository.findUserEntityByEmail(authRequestDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            log.info("Usuario encontrado: {}", userEntity.getEmail());

            String passwordRecibida = authRequestDto.getPassword();
            String passwordEnBD = userEntity.getPassword();

            log.info("Contraseña recibida (texto plano): {}", passwordRecibida);
            log.info("Contraseña en BD (hash): {}", passwordEnBD);
            log.info("Tipo de PasswordEncoder: {}", passwordEncoder.getClass().getSimpleName());

            boolean passwordMatch = passwordEncoder.matches(passwordRecibida, passwordEnBD);
            log.info("¿Contraseña correcta?: {}", passwordMatch);

            if (!passwordMatch) {
                log.warn("Contraseña incorrecta para usuario: {}", authRequestDto.getEmail());

                // Debug: Intentar con BCrypt directo
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                boolean debugMatch = bCryptPasswordEncoder.matches(passwordRecibida, passwordEnBD);
                log.warn("Debug con BCrypt directo: {}", debugMatch);

                throw new BadCredentialsException("Incorrect Password");
            }

            UserDetails userDetails = loadUserByUsername(authRequestDto.getEmail());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            null,
                            userDetails.getAuthorities()
                    );

            String token = jwtUtil.createToken(authentication, ((CustomUserDetails) userDetails).getId());
            log.info("Token generado exitosamente para: {}", authRequestDto.getEmail());

            return new AuthResponseDto(token, "Login successful");
        } catch (Exception e) {
            log.error("Error durante el login: ", e);
            throw e;
        }
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
