package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(PetDto petDto, Long userId);

    List<Pet> getPetsByUserId(Long userId);

    Pet getPetByIdAndUserId(Long id, Long userId);

    Pet updatePet(Long id, PetDto petDto, Long userId);

    void deletePet(Long id, Long userId);
}
