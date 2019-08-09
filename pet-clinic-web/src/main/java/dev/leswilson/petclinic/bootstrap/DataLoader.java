package dev.leswilson.petclinic.bootstrap;

import dev.leswilson.petclinic.model.*;
import dev.leswilson.petclinic.services.OwnerService;
import dev.leswilson.petclinic.services.PetTypeService;
import dev.leswilson.petclinic.services.SpecialityService;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {

        int count = petTypeService.findAll().size();
        if(count > 0) {
            return;
        }
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
        addAddress(owner1, "123 Low Street", "", "Buckhurst Hill", "Essex", "UK", "IG10 6GG");
        owner1.setTelephone("0208 765 4321");

        Pet pet1 = new Pet();
        pet1.setBirthDate(LocalDate.of(2015, 11, 12));
        pet1.setPetType(dog);
        pet1.setName("Micky");
        pet1.setOwner(owner1);

        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Apollo");
        owner2.setLastName("Creed");
        addAddress(owner2, "123 High Street", "", "Woodford", "Greater London", "UK", "IG9 6AA");
        owner2.setTelephone("0208 123 4567");

        Pet pet2 = new Pet();
        pet2.setBirthDate(LocalDate.of(2017, 1, 23));
        pet2.setPetType(cat);
        pet2.setName("Ricky");
        pet2.setOwner(owner2);

        Pet pet3 = new Pet();
        pet3.setBirthDate(LocalDate.of(2015, 11, 12));
        pet3.setPetType(horse);
        pet3.setName("Nellie");
        pet3.setOwner(owner1);

        owner2.getPets().add(pet3);
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        specialityService.save(surgery);

        Speciality aquatic = new Speciality();
        aquatic.setDescription("Aquatic");
        specialityService.save(aquatic);

        Speciality birds = new Speciality();
        birds.setDescription("Birds");
        specialityService.save(birds);

        Vet vet1 = new Vet();
        vet1.setFirstName("Adrienne");
        vet1.setLastName("Balboa");
        vet1.getSpecialities().add(surgery);
        vet1.getSpecialities().add(aquatic);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mrs");
        vet2.setLastName("Creed");
        vet2.getSpecialities().add(surgery);
        vet2.getSpecialities().add(birds);
        vetService.save(vet2);
    }

    private void addAddress(Owner owner, String line1, String line2, String city, String county, String country, String postcode) {
        Address address = new Address();
        address.setLine1(line1);
        address.setLine2(line2);
        address.setCity(city);
        address.setCounty(county);
        address.setCountry(country);
        address.setPostcode(postcode);
        owner.setAddress(address);
    }
}
