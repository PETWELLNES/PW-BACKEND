package com.petwellnes.petwellnes_backend.infra.repository;

import com.petwellnes.petwellnes_backend.model.entity.VetVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetVisitRepository extends JpaRepository<VetVisit, Long> {
    List<VetVisit> findByPetId(Long petId);
}
