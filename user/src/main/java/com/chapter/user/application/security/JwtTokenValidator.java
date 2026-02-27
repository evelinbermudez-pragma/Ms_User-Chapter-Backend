package com.chapter.user.application.security;
import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTokenValidator extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;

    public JwtTokenValidator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            try {
                jwtToken = jwtToken.substring(7);

                DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);

                String username = jwtUtil.extractUsername(decodedJWT);
                Long userId = jwtUtil.extractUserId(decodedJWT);  // Extraer el ID del usuario
                String stringAuthorities = jwtUtil.getSpecificClaim(decodedJWT, "authorities").asString();

                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

                // Crear un objeto que contenga tanto el ID del usuario como el nombre de usuario
                CustomUserDetails userDetails = new CustomUserDetails(userId, username, "[PROTECTED]", authorities);

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);  // Usar el CustomUserDetails
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
            } catch (JWTVerificationException exception) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
