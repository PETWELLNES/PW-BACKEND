package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;
import com.petwellnes.petwellnes_backend.service.PetService;
import com.petwellnes.petwellnes_backend.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/create")
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto, @AuthenticationPrincipal User user) {
        Pet newPet = petService.createPet(petDto, user.getUserId());
        return ResponseEntity.ok(newPet);
    }

    @GetMapping("/selectbyuser/{userId}")
    public ResponseEntity<List<Pet>> getPetsByUser(@AuthenticationPrincipal User user) {
        List<Pet> pets = petService.getPetsByUserId(user.getUserId());
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Pet pet = petService.getPetByIdAndUserId(id, user.getUserId());
        return ResponseEntity.ok(pet);
    }
}
