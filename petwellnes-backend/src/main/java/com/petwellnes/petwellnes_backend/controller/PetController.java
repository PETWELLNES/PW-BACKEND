package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.dto.petDto.PetDto;
import com.petwellnes.petwellnes_backend.model.entity.Pet;
import com.petwellnes.petwellnes_backend.service.PetService;
import com.petwellnes.petwellnes_backend.infra.config.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        Pet newPet = petService.createPet(petDto, userId);
        return ResponseEntity.ok(newPet);
    }

    @GetMapping("/selectbyuser")
    public ResponseEntity<List<Pet>> getPetsByUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        List<Pet> pets = petService.getPetsByUserId(userId);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        Pet pet = petService.getPetByIdAndUserId(id, userId);
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody PetDto petDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        Pet updatedPet = petService.updatePet(id, petDto, userId);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        petService.deletePet(id, userId);
        return ResponseEntity.noContent().build();
    }
}
