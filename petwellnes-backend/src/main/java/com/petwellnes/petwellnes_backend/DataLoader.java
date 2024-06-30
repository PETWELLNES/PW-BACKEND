package com.petwellnes.petwellnes_backend;

import com.petwellnes.petwellnes_backend.infra.repository.PetBreedRepository;
import com.petwellnes.petwellnes_backend.infra.repository.PetTypeRepository;
import com.petwellnes.petwellnes_backend.model.entity.PetBreed;
import com.petwellnes.petwellnes_backend.model.entity.PetType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final PetTypeRepository petTypeRepository;
    private final PetBreedRepository petBreedRepository;

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Perro");
        petTypeRepository.save(dog);

        addPetBreed("Labrador Retriever", dog);
        addPetBreed("Bulldog", dog);
        addPetBreed("Beagle", dog);
        addPetBreed("Poodle", dog);
        addPetBreed("Rottweiler", dog);
        addPetBreed("Yorkshire Terrier", dog);
        addPetBreed("Boxer", dog);
        addPetBreed("Dachshund", dog);
        addPetBreed("Pastor Alemán", dog);
        addPetBreed("Golden Retriever", dog);
        addPetBreed("Shih Tzu", dog);
        addPetBreed("Doberman Pinscher", dog);
        addPetBreed("Chihuahua", dog);
        addPetBreed("Gran Danés", dog);
        addPetBreed("Husky Siberiano", dog);
        addPetBreed("Callejero", dog);
        addPetBreed("No Identificado", dog);

        PetType cat = new PetType();
        cat.setName("Gato");
        petTypeRepository.save(cat);

        addPetBreed("Siamés", cat);
        addPetBreed("Persa", cat);
        addPetBreed("Maine Coon", cat);
        addPetBreed("Ragdoll", cat);
        addPetBreed("British Shorthair", cat);
        addPetBreed("Abisinio", cat);
        addPetBreed("Bengala", cat);
        addPetBreed("Sphynx", cat);
        addPetBreed("Scottish Fold", cat);
        addPetBreed("Azul Ruso", cat);
        addPetBreed("Savannah", cat);
        addPetBreed("Bosque de Noruega", cat);
        addPetBreed("Oriental Shorthair", cat);
        addPetBreed("Angora Turco", cat);
        addPetBreed("Callejero", cat);
        addPetBreed("No Identificado", cat);

        PetType fish = new PetType();
        fish.setName("Pez");
        petTypeRepository.save(fish);

        addPetBreed("Pez Payaso", fish);
        addPetBreed("Pez Dorado", fish);
        addPetBreed("Betta", fish);
        addPetBreed("Guppy", fish);
        addPetBreed("Tetra", fish);
        addPetBreed("Pez Ángel", fish);
        addPetBreed("Cíclido", fish);
        addPetBreed("Bagre", fish);
        addPetBreed("Molly", fish);
        addPetBreed("Pez Espada", fish);
        addPetBreed("Platy", fish);
        addPetBreed("Óscar", fish);
        addPetBreed("Koi", fish);
        addPetBreed("Gurami", fish);
        addPetBreed("Neón Tetra", fish);
        addPetBreed("Disco", fish);
        addPetBreed("No Identificado", fish);

        PetType rodent = new PetType();
        rodent.setName("Roedor");
        petTypeRepository.save(rodent);

        addPetBreed("Cuy", rodent);
        addPetBreed("Conejo", rodent);
        addPetBreed("Hámster", rodent);
        addPetBreed("Ratón", rodent);
        addPetBreed("Rata", rodent);
        addPetBreed("Jerbo", rodent);
        addPetBreed("Chinchilla", rodent);
        addPetBreed("Ardilla", rodent);
        addPetBreed("Perro de la pradera", rodent);
        addPetBreed("Degu", rodent);
        addPetBreed("No Identificado", rodent);

        PetType bird = new PetType();
        bird.setName("Ave");
        petTypeRepository.save(bird);

        addPetBreed("Periquito", bird);
        addPetBreed("Canario", bird);
        addPetBreed("Cacatúa", bird);
        addPetBreed("Agapornis", bird);
        addPetBreed("Pinzón", bird);
        addPetBreed("Pájaro Budgie", bird);
        addPetBreed("Loro", bird);
        addPetBreed("Guacamayo", bird);
        addPetBreed("Cacatúa", bird);
        addPetBreed("Conuro", bird);
        addPetBreed("Gris Africano", bird);
        addPetBreed("Ecléctico", bird);
        addPetBreed("Loriquito", bird);
        addPetBreed("Paloma", bird);
        addPetBreed("Tórtola", bird);
        addPetBreed("No Identificado", bird);

        PetType reptile = new PetType();
        reptile.setName("Reptil");
        petTypeRepository.save(reptile);

        addPetBreed("Tortuga", reptile);
        addPetBreed("Camaleón", reptile);
        addPetBreed("Iguana", reptile);
        addPetBreed("Geco", reptile);
        addPetBreed("Dragón Barbudo", reptile);
        addPetBreed("Serpiente", reptile);
        addPetBreed("Tortuga de Tierra", reptile);
        addPetBreed("Lagarto Monitor", reptile);
        addPetBreed("Anole", reptile);
        addPetBreed("Eslizón", reptile);
        addPetBreed("Boa", reptile);
        addPetBreed("Pitón", reptile);
        addPetBreed("Cobra", reptile);
        addPetBreed("Caimán", reptile);
        addPetBreed("Cocodrilo", reptile);
        addPetBreed("No Identificado", reptile);

        PetType other = new PetType();
        other.setName("Otro");
        petTypeRepository.save(other);

        addPetBreed("Caballo", other);
        addPetBreed("Oveja", other);
        addPetBreed("Cabra", other);
        addPetBreed("Cerdo", other);
        addPetBreed("Vaca", other);
        addPetBreed("Llama", other);
        addPetBreed("Alpaca", other);
        addPetBreed("Burro", other);
        addPetBreed("No Identificado", other);
    }

    private void addPetBreed(String breedName, PetType petType) {
        PetBreed breed = new PetBreed();
        breed.setName(breedName);
        breed.setPetType(petType);
        petBreedRepository.save(breed);
    }
}
