package com.petwellnes.petwellnes_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private Date fecha;

    @Column(columnDefinition = "TIME")
    private Time hora;

    @Column(nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tema", nullable = false)
    private Topic tema;

    @ManyToOne
    @JoinColumn(name = "tipo_mascota", nullable = false)
    private TipoMascota tipoMascota;

    @Column(length = 10485760)
    private String img;

    @Column(length = 10485760)
    private String video;

    @Column(nullable = false, length = 10485760)
    private String contenido;

    @Column
    private String enlace;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacion")
    private List<Comment> comentaries;

    @Column
    private int reacciones = 0;

    @ManyToOne
    @JoinColumn(name = "raza")
    private RazaAnimal raza;

    public Publicacion(User user, String categoria, Topic tema, TipoMascota tipoMascota, String img, String video, String contenido, String enlace) {
        this.fecha = new Date();
        this.hora = new Time(System.currentTimeMillis());
        this.user = user;
        this.categoria = categoria;
        this.tema = tema;
        this.tipoMascota = tipoMascota;
        this.img = img;
        this.video = video;
        this.contenido = contenido;
        this.enlace = enlace;
    }

    public Publicacion(User user, String categoria, Topic tema, TipoMascota tipoMascota, String img, String video, String contenido, String enlace, RazaAnimal raza) {
        this.fecha = new Date();
        this.hora = new Time(System.currentTimeMillis());
        this.user = user;
        this.categoria = categoria;
        this.tema = tema;
        this.tipoMascota = tipoMascota;
        this.img = img;
        this.video = video;
        this.contenido = contenido;
        this.enlace = enlace;
        this.raza = raza;
    }

    public Publicacion() {
        this.fecha = new Date();
        this.hora = new Time(System.currentTimeMillis());
    }
}
