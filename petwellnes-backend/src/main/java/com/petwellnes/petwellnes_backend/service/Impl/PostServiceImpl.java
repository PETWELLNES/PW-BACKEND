package com.petwellnes.petwellnes_backend.service.Impl;

import com.petwellnes.petwellnes_backend.infra.repository.PetBreedRepository;
import com.petwellnes.petwellnes_backend.infra.repository.PetTypeRepository;
import com.petwellnes.petwellnes_backend.infra.repository.PostRepository;
import com.petwellnes.petwellnes_backend.infra.repository.UserRepository;
import com.petwellnes.petwellnes_backend.mapper.PostMapper;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostCreateDTO;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostDTO;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostUpdateDTO;
import com.petwellnes.petwellnes_backend.model.entity.PetBreed;
import com.petwellnes.petwellnes_backend.model.entity.PetType;
import com.petwellnes.petwellnes_backend.model.entity.Post;
import com.petwellnes.petwellnes_backend.model.entity.User;
import com.petwellnes.petwellnes_backend.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetBreedRepository petBreedRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostDTO createPost(PostCreateDTO postCreateDTO) {
        User user = userRepository.findById(postCreateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        PetType petType = petTypeRepository.findById(postCreateDTO.getPetTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de mascota no encontrado"));

        PetBreed petBreed = petBreedRepository.findById(postCreateDTO.getPetBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Raza de mascota no encontrada"));

        if (!petBreed.getPetType().getId().equals(petType.getId())) {
            throw new IllegalArgumentException("La raza de mascota no corresponde al tipo de mascota.");
        }

        Post post = postMapper.convertToEntity(postCreateDTO);
        post.setUser(user);
        post.setPetType(petType);
        post.setPetBreed(petBreed);
        postRepository.save(post);
        return postMapper.convertToDTO(post);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        return postMapper.convertToDTO(post);
    }

    @Override
    public List<PostDTO> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable).stream()
                .map(postMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDTO updatePost(Long postId, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));

        PetType petType = petTypeRepository.findById(postUpdateDTO.getPetTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de mascota no encontrado"));

        PetBreed petBreed = petBreedRepository.findById(postUpdateDTO.getPetBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Raza de mascota no encontrada"));

        if (!petBreed.getPetType().getId().equals(petType.getId())) {
            throw new IllegalArgumentException("La raza de mascota no corresponde al tipo de mascota.");
        }

        post.setTitle(postUpdateDTO.getTitle());
        post.setContent(postUpdateDTO.getContent());
        post.setPetType(petType);
        post.setPetBreed(petBreed);
        postRepository.save(post);

        return postMapper.convertToDTO(post);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        postRepository.delete(post);
    }
}
