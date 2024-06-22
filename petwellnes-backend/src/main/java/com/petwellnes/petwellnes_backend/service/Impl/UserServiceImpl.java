package com.petwellnes.petwellnes_backend.service.Impl;

import com.petwellnes.petwellnes_backend.infra.config.security.LoginRequest;
import com.petwellnes.petwellnes_backend.infra.config.security.TokenResponse;
import com.petwellnes.petwellnes_backend.infra.exception.UsernameNotFoundException;
import com.petwellnes.petwellnes_backend.infra.repository.UserRepository;
import com.petwellnes.petwellnes_backend.model.dto.userDto.UserRegisterDTO;
import com.petwellnes.petwellnes_backend.model.entity.User;
import com.petwellnes.petwellnes_backend.infra.config.security.JwtService;
import com.petwellnes.petwellnes_backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("El siguiente username es incorrecto o no existe :" + loginRequest.getUsername()));

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public TokenResponse addUser(UserRegisterDTO data) {
        User user = new User();
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPassword(passwordEncoder.encode(data.password()));

        userRepository.save(user);

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("No hay usuario autenticado");
        }

        String currentUserName = auth.getName();
        return userRepository.findByUsername(currentUserName)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    private void UserValidate(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new UsernameNotFoundException("El username no puede ser vacío");
        }
    }
}
