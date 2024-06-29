package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.entity.User;

public interface UserService {
    User findUser(Long id);
}
