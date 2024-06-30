package com.petwellnes.petwellnes_backend.model.dto.postDto;

import com.petwellnes.petwellnes_backend.model.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private Long userId;
    private Long petTypeId;
    private String petTypeName;
    private Long petBreedId;
    private String petBreedName;

    public PostDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getUserId();
        this.petTypeId = post.getPetType().getId();
        this.petTypeName = post.getPetType().getName();
        this.petBreedId = post.getPetBreed().getId();
        this.petBreedName = post.getPetBreed().getName();
    }
}
