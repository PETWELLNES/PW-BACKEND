package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;

public interface PetService {
    Pet createPet(PetDto petDto, Long userId);
}
