package com.petwellnes.petwellnes_backend.infra.repository;

import com.petwellnes.petwellnes_backend.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.user.userId = :userId")
    List<Pet> findByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Pet p WHERE p.id = :id AND p.user.userId = :userId")
    Optional<Pet> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);


}
