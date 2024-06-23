package com.petwellnes.petwellnes_backend.model.entity.dto;

import lombok.Data;

@Data
public class CreatePostDTO {
    private String category;
    private Long topic;
    private Long petType;
    private String image;
    private String video;
    private String content;
    private String link;
    private Long animalBreed;
}