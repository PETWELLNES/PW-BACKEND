package com.petwellnes.petwellnes_backend.service.Impl;

import com.petwellnes.petwellnes_backend.model.entity.User;
import com.petwellnes.petwellnes_backend.infra.repository.UserRepository;
import com.petwellnes.petwellnes_backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }
}
