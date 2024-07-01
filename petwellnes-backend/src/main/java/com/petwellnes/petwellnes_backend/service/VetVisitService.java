package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.dto.vetvisitDto.VetVisitDTO;
import com.petwellnes.petwellnes_backend.model.entity.VetVisit;

import java.util.List;
import java.util.Optional;

public interface VetVisitService {
    List<VetVisit> getAllVetVisitsByPetId(Long petId);
    Optional<VetVisit> getVetVisitById(Long id);
    VetVisit createVetVisit(VetVisitDTO vetVisitDTO);
    VetVisit updateVetVisit(Long id, VetVisitDTO vetVisitDTO);
    void deleteVetVisit(Long id);
}