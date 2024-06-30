package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.entity.PetBreed;
import com.petwellnes.petwellnes_backend.service.PetBreedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pet-breed")
@RequiredArgsConstructor
public class PetBreedController {

    private final PetBreedService petBreedService;

    @PostMapping
    public ResponseEntity<PetBreed> createPetBreed(@RequestBody @Valid PetBreed petBreed) {
        PetBreed createdPetBreed = petBreedService.createPetBreed(petBreed);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPetBreed);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetBreed> getPetBreedById(@PathVariable Long id) {
        PetBreed petBreed = petBreedService.getPetBreedById(id);
        return ResponseEntity.ok(petBreed);
    }

    @GetMapping
    public ResponseEntity<List<PetBreed>> getAllPetBreeds() {
        List<PetBreed> petBreeds = petBreedService.getAllPetBreeds();
        return ResponseEntity.ok(petBreeds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetBreed> updatePetBreed(@PathVariable Long id, @RequestBody @Valid PetBreed petBreed) {
        PetBreed updatedPetBreed = petBreedService.updatePetBreed(id, petBreed);
        return ResponseEntity.ok(updatedPetBreed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetBreed(@PathVariable Long id) {
        petBreedService.deletePetBreed(id);
        return ResponseEntity.noContent().build();
    }
}
