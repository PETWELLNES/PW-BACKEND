package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.infra.config.security.LoginRequest;
import com.petwellnes.petwellnes_backend.infra.config.security.TokenResponse;
import com.petwellnes.petwellnes_backend.model.dto.userDto.UserRegisterDTO;
import com.petwellnes.petwellnes_backend.model.entity.User;

public interface UserService {

    TokenResponse login(LoginRequest request);

    TokenResponse addUser(UserRegisterDTO user);

    User getAuthUser();
}
