package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.dto.vetvisitDto.VetVisitDTO;
import com.petwellnes.petwellnes_backend.model.entity.VetVisit;
import com.petwellnes.petwellnes_backend.service.VetVisitService;
import com.petwellnes.petwellnes_backend.infra.config.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vetvisits")
public class VetVisitController {

    @Autowired
    private VetVisitService vetVisitService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<VetVisit> createVetVisit(@Valid @RequestBody VetVisitDTO vetVisitDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        VetVisit newVetVisit = vetVisitService.createVetVisit(vetVisitDTO, userId);
        return ResponseEntity.ok(newVetVisit);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<VetVisit>> getAllVetVisitsByPetId(@PathVariable Long petId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        List<VetVisit> vetVisits = vetVisitService.getAllVetVisitsByPetId(petId, userId);
        return ResponseEntity.ok(vetVisits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetVisit> getVetVisitById(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        VetVisit vetVisit = vetVisitService.getVetVisitById(id, userId)
                .orElseThrow(() -> new RuntimeException("VetVisit not found"));
        return ResponseEntity.ok().body(vetVisit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetVisit> updateVetVisit(@PathVariable Long id, @Valid @RequestBody VetVisitDTO vetVisitDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        VetVisit updatedVetVisit = vetVisitService.updateVetVisit(id, vetVisitDTO, userId);
        return ResponseEntity.ok(updatedVetVisit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVetVisit(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        vetVisitService.deleteVetVisit(id, userId);
        return ResponseEntity.noContent().build();
    }
}
