package com.petwellnes.petwellnes_backend.infra.repository;

import com.petwellnes.petwellnes_backend.model.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<Post> findByUserUserId(Long userId);
}
