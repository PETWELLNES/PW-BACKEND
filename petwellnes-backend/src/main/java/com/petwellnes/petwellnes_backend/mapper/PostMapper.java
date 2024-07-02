package com.petwellnes.petwellnes_backend.mapper;

import com.petwellnes.petwellnes_backend.model.dto.postDto.PostCreateDTO;
import com.petwellnes.petwellnes_backend.model.dto.postDto.PostDTO;
import com.petwellnes.petwellnes_backend.model.entity.Post;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Date;

@Component
public class PostMapper {

    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setDate(Date.from(post.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()));
        postDTO.setTime(Time.valueOf(post.getCreatedAt().toLocalTime()));
        postDTO.setCategory(post.getCategory());
        postDTO.setUserId(post.getUser().getUserId());
        postDTO.setTopicId(post.getTopic().getId());
        postDTO.setPetTypeId(post.getPetType().getPetTypeId());
        postDTO.setImage(post.getImage());
        postDTO.setVideo(post.getVideo());
        postDTO.setContent(post.getContent());
        postDTO.setLink(post.getLink());
        postDTO.setReactions(post.getReactions());
        postDTO.setBreedId(post.getPetBreed().getPetBreedId());
        return postDTO;
    }
}
