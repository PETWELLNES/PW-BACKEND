package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;
import com.petwellnes.petwellnes_backend.service.PetService;
import com.petwellnes.petwellnes_backend.infra.repository.UserRepository;
import com.petwellnes.petwellnes_backend.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Retornar Not Found si el usuario no existe
        }
        Pet newPet = petService.createPet(petDto, userId);
        return ResponseEntity.ok(newPet);
    }

    @GetMapping("/selectbyuser/{userId}")
    public ResponseEntity<List<Pet>> getPetsByUser(@PathVariable Long userId) {
        List<Pet> pets = petService.getPetsByUserId(userId);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}/{userId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id, @PathVariable Long userId) {
        Pet pet = petService.getPetByIdAndUserId(id, userId);
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}/{userId}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody PetDto petDto, @PathVariable Long userId) {
        Pet updatedPet = petService.updatePet(id, petDto, userId);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id, @PathVariable Long userId) {
        petService.deletePet(id, userId);
        return ResponseEntity.noContent().build();
    }

}
