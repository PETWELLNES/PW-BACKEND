package com.petwellnes.petwellnes_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
