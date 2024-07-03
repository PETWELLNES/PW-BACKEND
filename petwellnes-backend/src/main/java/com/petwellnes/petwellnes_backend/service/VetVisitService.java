package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.dto.vetvisitDto.VetVisitDTO;
import com.petwellnes.petwellnes_backend.model.entity.VetVisit;

import java.util.List;
import java.util.Optional;

public interface VetVisitService {
    List<VetVisit> getAllVetVisitsByPetId(Long petId, Long userId);

    Optional<VetVisit> getVetVisitById(Long id, Long userId);

    VetVisit createVetVisit(VetVisitDTO vetVisitDTO, Long userId);

    VetVisit updateVetVisit(Long id, VetVisitDTO vetVisitDTO, Long userId);

    void deleteVetVisit(Long id, Long userId);
}
