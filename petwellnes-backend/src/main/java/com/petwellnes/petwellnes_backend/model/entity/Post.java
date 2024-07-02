package com.petwellnes.petwellnes_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;  // Cambiado a postId para que coincida con el repositorio

    @Column(columnDefinition = "DATE")
    private Date date;

    @Column(columnDefinition = "TIME")
    private Time time;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "pet_breed_id", nullable = false)
    private PetBreed petBreed;

    @Column(length = 10485760)
    private String image;

    @Column(length = 10485760)
    private String video;

    @Column(nullable = false, length = 10485760)
    private String content;

    @Column
    private String link;

    @Column
    private int reactions = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructores
    public Post(User user, String category, Long topicId, Long petTypeId, String image, String video, String content, String link) {
        this.date = new Date();
        this.time = new Time(System.currentTimeMillis());
        this.user = user;
        this.category = category;
        this.topic = new Topic();
        this.topic.setId(topicId);
        this.petType = new PetType();
        this.petType.setPetTypeId(petTypeId);
        this.image = image;
        this.video = video;
        this.content = content;
        this.link = link;
    }

    public Post(User user, String category, Long topicId, Long petTypeId, String image, String video, String content, String link, Long breedId) {
        this(user, category, topicId, petTypeId, image, video, content, link);
        if (breedId != null) {
            this.petBreed = new PetBreed();
            this.petBreed.setPetBreedId(breedId);
        }
    }
}
