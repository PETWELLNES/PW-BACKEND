package com.petwellnes.petwellnes_backend.service.Impl;

import com.petwellnes.petwellnes_backend.model.dto.vetvisitDto.VetVisitDTO;
import com.petwellnes.petwellnes_backend.model.entity.Pet;
import com.petwellnes.petwellnes_backend.model.entity.VetVisit;
import com.petwellnes.petwellnes_backend.infra.exception.CustomException;
import com.petwellnes.petwellnes_backend.infra.repository.PetRepository;
import com.petwellnes.petwellnes_backend.infra.repository.VetVisitRepository;
import com.petwellnes.petwellnes_backend.service.VetVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetVisitServiceImpl implements VetVisitService {

    @Autowired
    private VetVisitRepository vetVisitRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<VetVisit> getAllVetVisitsByPetId(Long petId) {
        return vetVisitRepository.findByPetId(petId);
    }

    @Override
    public Optional<VetVisit> getVetVisitById(Long id) {
        return vetVisitRepository.findById(id);
    }

    @Override
    public VetVisit createVetVisit(VetVisitDTO vetVisitDTO) {
        Pet pet = petRepository.findById(vetVisitDTO.getPetId())
                .orElseThrow(() -> new CustomException("Pet not found"));

        VetVisit vetVisit = new VetVisit();
        vetVisit.setDate(vetVisitDTO.getDate());
        vetVisit.setReason(vetVisitDTO.getReason());
        vetVisit.setNotes(vetVisitDTO.getNotes());
        vetVisit.setPet(pet);

        return vetVisitRepository.save(vetVisit);
    }

    @Override
    public VetVisit updateVetVisit(Long id, VetVisitDTO vetVisitDTO) {
        VetVisit vetVisit = vetVisitRepository.findById(id)
                .orElseThrow(() -> new CustomException("VetVisit not found"));

        vetVisit.setDate(vetVisitDTO.getDate());
        vetVisit.setReason(vetVisitDTO.getReason());
        vetVisit.setNotes(vetVisitDTO.getNotes());

        return vetVisitRepository.save(vetVisit);
    }

    @Override
    public void deleteVetVisit(Long id) {
        VetVisit vetVisit = vetVisitRepository.findById(id)
                .orElseThrow(() -> new CustomException("VetVisit not found"));
        vetVisitRepository.delete(vetVisit);
    }
}