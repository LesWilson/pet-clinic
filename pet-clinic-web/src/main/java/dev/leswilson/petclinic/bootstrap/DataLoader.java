package dev.leswilson.petclinic.bootstrap;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.OwnerService;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Rocky");
        owner1.setLastName("Balboa");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Apollo");
        owner2.setLastName("Creed");
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Adrienne");
        vet1.setLastName("Balboa");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Mrs");
        vet2.setLastName("Creed");
        vetService.save(vet2);
    }
}
