package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.model.dto.vetvisitDto.VetVisitDTO;
import com.petwellnes.petwellnes_backend.model.entity.VetVisit;
import com.petwellnes.petwellnes_backend.infra.exception.CustomException;
import com.petwellnes.petwellnes_backend.service.VetVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vetvisits")
public class VetVisitController {

    @Autowired
    private VetVisitService vetVisitService;

    @GetMapping("/pet/{petId}")
    public List<VetVisit> getAllVetVisitsByPetId(@PathVariable Long petId) {
        return vetVisitService.getAllVetVisitsByPetId(petId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetVisit> getVetVisitById(@PathVariable Long id) {
        VetVisit vetVisit = vetVisitService.getVetVisitById(id)
                .orElseThrow(() -> new CustomException("VetVisit not found"));
        return ResponseEntity.ok().body(vetVisit);
    }

    @PostMapping
    public VetVisit createVetVisit(@Valid @RequestBody VetVisitDTO vetVisitDTO) {
        return vetVisitService.createVetVisit(vetVisitDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetVisit> updateVetVisit(@PathVariable Long id,
                                                   @Valid @RequestBody VetVisitDTO vetVisitDTO) {
        VetVisit updatedVetVisit = vetVisitService.updateVetVisit(id, vetVisitDTO);
        return ResponseEntity.ok(updatedVetVisit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVetVisit(@PathVariable Long id) {
        vetVisitService.deleteVetVisit(id);
        return ResponseEntity.noContent().build();
    }
}