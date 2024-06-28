package com.petwellnes.petwellnes_backend.service.Impl;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;
import com.petwellnes.petwellnes_backend.model.entity.User;
import com.petwellnes.petwellnes_backend.infra.repository.PetRepository;
import com.petwellnes.petwellnes_backend.infra.repository.UserRepository;
import com.petwellnes.petwellnes_backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Pet createPet(PetDto petDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setSpecies(petDto.getSpecies());
        pet.setBreed(petDto.getBreed());
        pet.setAge(petDto.getAge());
        pet.setPhoto(petDto.getPhoto());
        pet.setUser(user);

        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetsByUserId(Long userId) {
        return petRepository.findByUserId(userId);
    }

    @Override
    public Pet getPetByIdAndUserId(Long id, Long userId) {
        return petRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }
}
