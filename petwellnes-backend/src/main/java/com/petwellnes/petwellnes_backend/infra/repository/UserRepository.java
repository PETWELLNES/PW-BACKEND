package com.petwellnes.petwellnes_backend.infra.repository;

import com.petwellnes.petwellnes_backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
