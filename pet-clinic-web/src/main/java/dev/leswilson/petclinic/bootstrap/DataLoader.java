package dev.leswilson.petclinic.bootstrap;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.PetType;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.OwnerService;
import dev.leswilson.petclinic.services.PetTypeService;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {

        PetType dog = new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);

        PetType horse = new PetType();
        horse.setName("Horse");
        horse = petTypeService.save(horse);

        Owner owner1 = new Owner();
        owner1.setFirstName("Rocky");
        owner1.setLastName("Balboa");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Apollo");
        owner2.setLastName("Creed");
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Adrienne");
        vet1.setLastName("Balboa");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mrs");
        vet2.setLastName("Creed");
        vetService.save(vet2);
    }
}
