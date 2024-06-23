package com.petwellnes.petwellnes_backend.model.entity.dto;

import java.time.LocalDate;

public record UserUpdateDTO(
        String username,
        String name,
        String lastname,
        String email,
        String phone,
        String work,
        LocalDate birthday,
        String country,
        String password
) {
}
