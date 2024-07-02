package com.petwellnes.petwellnes_backend.model.dto.postDto;

import lombok.Data;

@Data
public class PostCreateDTO {
    private Long userId;
    private Long petTypeId;
    private Long petBreedId;
    private Long topicId;
    private String title;
    private String category;
    private String image;
    private String video;
    private String content;
    private String link;
}
