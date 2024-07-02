package com.petwellnes.petwellnes_backend.model.dto.postDto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class PostDTO {
    private Long postId;
    private Date date;
    private Time time;
    private String category;
    private Long userId;
    private Long topicId;
    private Long petTypeId;
    private String image;
    private String video;
    private String content;
    private String link;
    private int reactions;
    private Long breedId;

    // Getters and Setters
    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }
}
