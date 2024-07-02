package com.petwellnes.petwellnes_backend.controller;

import com.petwellnes.petwellnes_backend.infra.config.security.JwtService;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostCreateDTO;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostDTO;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostUpdateDTO;
import com.petwellnes.petwellnes_backend.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    // Crear una nueva publicación
    @PostMapping("/create_post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateDTO postCreateDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        postCreateDTO.setUserId(userId); // Set the userId from the token
        PostDTO newPost = postService.createPost(postCreateDTO);
        return ResponseEntity.ok(newPost);
    }

    // Obtener una publicación por su ID
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // Obtener todas las publicaciones con paginación
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(postService.getAllPosts(page, size));
    }

    // Obtener todas las publicaciones del usuario autenticado
    @GetMapping("/user")
    public ResponseEntity<List<PostDTO>> getPostsByUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.getUserIdFromToken(token);
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    // Actualizar una publicación
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostUpdateDTO postUpdateDTO) {
        return ResponseEntity.ok(postService.updatePost(postId, postUpdateDTO));
    }

    // Eliminar una publicación
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    // Obtener las publicaciones más recientes
    @GetMapping("/recent")
    public ResponseEntity<List<PostDTO>> getRecentPosts() {
        return ResponseEntity.ok(postService.getRecentPosts());
    }

    // Filtrar publicaciones por tipo de mascota
    @GetMapping("/discover/filterByPetType/{petType}")
    public ResponseEntity<List<PostDTO>> filterByPetType(@PathVariable String petType) {
        List<PostDTO> posts = postService.filterPostsByPetType(petType); // Filtrar publicaciones por tipo de mascota
        return ResponseEntity.ok(posts); // Retornar las publicaciones filtradas
    }

    // Filtrar publicaciones por raza
    @GetMapping("/discover/filterByBreed/{breed}")
    public ResponseEntity<List<PostDTO>> filterByBreed(@PathVariable String breed) {
        List<PostDTO> posts = postService.filterPostsByBreed(breed); // Filtrar publicaciones por raza
        return ResponseEntity.ok(posts); // Retornar las publicaciones filtradas
    }

    // Filtrar publicaciones por tipo de mascota y raza
    @GetMapping("/discover/filterByPetTypeAndBreed/{petType}/{breed}")
    public ResponseEntity<List<PostDTO>> filterByPetTypeAndBreed(@PathVariable String petType, @PathVariable String breed) {
        List<PostDTO> posts = postService.filterPostsByPetTypeAndBreed(petType, breed); // Filtrar publicaciones por tipo de mascota y raza
        return ResponseEntity.ok(posts); // Retornar las publicaciones filtradas
    }
}
