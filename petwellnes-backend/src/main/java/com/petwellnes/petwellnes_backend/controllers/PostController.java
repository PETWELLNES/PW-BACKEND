package com.petwellnes.petwellnes_backend.controllers;

import com.petwellnes.petwellnes_backend.model.entity.Publicacion;
import com.petwellnes.petwellnes_backend.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "URL FRONT")
@Tag(name = "Publicacion")
public class PostController {

    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComentarioService comentarioService;

    @Value("${publicaciones.imagenes}")
    private String rutaImagenes;

    // Crear Publicación
    @PostMapping("/descubre/{id}/crear_publicacion")
    public ResponseEntity<?> crearPublicacion(@RequestBody CrearPublicacionSerializer crearPublicacionSerializer, @PathVariable Long id) throws IOException {
        User user = usuarioService.encontrarUsuario(id);

        // Validaciones
        if (crearPublicacionSerializer.getContenido() == null || crearPublicacionSerializer.getContenido().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe llenar todos los campos requeridos.");
        }

        if (crearPublicacionSerializer.getContenido().length() < 100) {
            return ResponseEntity.badRequest().body("Debe ingresar más de 100 caracteres para poder publicar.");
        }

        Publicacion publicacion;
        if (crearPublicacionSerializer.getRazaAnimal() == null) {
            publicacion = new Publicacion(user, crearPublicacionSerializer.getCategoria(), crearPublicacionSerializer.getTema(),
                    crearPublicacionSerializer.getTipoMascota(), crearPublicacionSerializer.getImg(), crearPublicacionSerializer.getVideo(),
                    crearPublicacionSerializer.getContenido(), crearPublicacionSerializer.getEnlace());
        } else {
            publicacion = new Publicacion(user, crearPublicacionSerializer.getCategoria(), crearPublicacionSerializer.getTema(),
                    crearPublicacionSerializer.getTipoMascota(), crearPublicacionSerializer.getImg(), crearPublicacionSerializer.getVideo(),
                    crearPublicacionSerializer.getContenido(), crearPublicacionSerializer.getEnlace(), crearPublicacionSerializer.getRazaAnimal());
        }

        publicacionService.crearPublicacion(publicacion);

        return ResponseEntity.ok(new PublicacionCreadaSerializer(publicacion.getFecha(), publicacion.getHora(), publicacion.getCategoria(),
                publicacion.getUser().getId(), publicacion.getTema(), publicacion.getTipoMascota(), publicacion.getEnlace(),
                publicacion.getContenido(), publicacion.getImg(), publicacion.getReacciones(), publicacion.getRaza()));
    }

    // Ver Mis Publicaciones
    @GetMapping("/descubre/{id}/mis_publicaciones")
    public ResponseEntity<List<PublicacionSerializer>> misPublicaciones(@PathVariable Long id) {
        User user = usuarioService.encontrarUsuario(id);
        List<PublicacionSerializer> publicaciones = publicacionService.verMisPublicaciones(user);
        return ResponseEntity.ok(publicaciones);
    }

    // Comentar Publicación
    @PostMapping("/descubre/{idU}/comentar/{idP}")
    public ResponseEntity<?> comentar(@RequestBody String contenido, @PathVariable Long idU, @PathVariable Long idP) {
        if (contenido.length() > 1000) { // Suponiendo un límite de 1000 caracteres para comentarios
            return ResponseEntity.badRequest().body("El comentario ha excedido el límite de caracteres permitido.");
        }

        User user = usuarioService.encontrarUsuario(idU);
        Publicacion publicacion = publicacionService.encontrarPublicacion(idP);
        Comentario comentario = new Comentario(contenido, user, publicacion);
        comentarioService.comentar(comentario);
        return ResponseEntity.ok(new ComentarioCreadoSerializer(comentario.getContenido(), comentario.getId(), comentario.getUsuario().getId(), comentario.getPublicacion().getId(), null));
    }

    // Ver Comentarios
    @GetMapping("/descubre/{id}/ver_comentarios")
    public ResponseEntity<?> verComentarios(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.encontrarPublicacion(id);
        List<ComentarioSerializer> comentarios = comentarioService.findAllByPublicacion(publicacion);
        if (comentarios.isEmpty()) {
            return ResponseEntity.ok("Sé el primero en dejar un comentario.");
        }
        return ResponseEntity.ok(comentarios);
    }

    // Reaccionar a Publicación
    @PutMapping("/descubre/{id}/reaccionar")
    public ResponseEntity<Integer> reaccionarPublicacion(@PathVariable Long id) {
        int reacciones = publicacionService.reaccionarPublicacion(id);
        return ResponseEntity.ok(reacciones);
    }

    // Filtro de Publicaciones
    @GetMapping("/descubre/{categoria}/{tipoMascota}/{raza}/{tema}")
    public ResponseEntity<List<PublicacionSerializer>> filtroPublicacion(@PathVariable String categoria, @PathVariable String tipoMascota, @PathVariable String raza, @PathVariable String tema) throws IOException {
        List<PublicacionSerializer> publicaciones = publicacionService.filtrarPublicaciones(categoria, tipoMascota, raza, tema);
        return ResponseEntity.ok(publicaciones);
    }

    // Eliminar Publicación
    @DeleteMapping("/descubre/{id}/eliminar")
    public ResponseEntity<?> eliminarPublicacion(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.encontrarPublicacion(id);
        if (publicacion != null) {
            publicacionService.eliminarPublicacion(publicacion);
            return ResponseEntity.ok("Publicación eliminada con éxito.");
        } else {
            return ResponseEntity.status(404).body("Publicación no encontrada.");
        }
    }

    // Compartir Publicación
    @GetMapping("/descubre/{id}/compartir")
    public ResponseEntity<String> compartirPublicacion(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.encontrarPublicacion(id);
        if (publicacion != null) {
            String link = "URL FRONT" + id;
            return ResponseEntity.ok(link);
        } else {
            return ResponseEntity.status(404).body("Publicación no encontrada.");
        }
    }
}

