package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class PetMapServiceTest {

    PetMapService service;

    Pet pet1, pet2, pet3;
    Owner owner;
    Set<Pet> pets;

    @BeforeEach
    void setUp() {
        service = new PetMapService();
    }

    @DisplayName("Given we have a list of Pets")
    @Nested
    class ExistingPetTest {
        @BeforeEach
        void setUp() {
            owner = new Owner();
            owner.setId(11L);

            pet1 = new Pet();
            pet1.setId(1L);
            pet1.setName("Rocky");
            pet1.setOwner(owner);
            service.save(pet1);

            pet2 = new Pet();
            pet2.setId(2L);
            pet2.setName("Rocky2");
            service.save(pet2);

            pet3 = new Pet();
            pet3.setId(3L);
            pet3.setName("Rocky3");
            service.save(pet3);

            pets = service.findAll();
        }

        @DisplayName("When we search for Pets")
        @Nested
        class FindPetTest {

            @Test
            @DisplayName("Then we can find all Pets")
            void findAll() {
                assertThat(pets, hasSize(3));
                assertThat(pets, hasItems(pet1, pet3, pet2));
            }

            @Test
            @DisplayName("Then we can find a Pet using an existing Id")
            void findByValidId() {
                Pet pet = service.findById(1L);
                assertThat(pet, is(notNullValue()));
                assertThat(pet, is(pet1));
                assertThat(pet.getId(), is(1L));
                assertThat(pet.getName(), is(pet1.getName()));
            }

            @Test
            @DisplayName("Then we do not find a Pet using an Id that doesn't exist")
            void findByInvalidId() {
                Pet pet = service.findById(11L);
                assertThat(pet, is(nullValue()));
            }

            @Test
            @DisplayName("Then we can find Pets by an Owner who has Pets")
            void findByOwnerReturnsRowsWhenOwnerWithPetsPassedIn() {
                Set<Pet> pets = service.findByOwner(owner);
                assertThat(pets, hasSize(1));
                assertThat(pets, hasItem(pet1));
            }

            @Test
            @DisplayName("Then we cannot find a Pet using an Owner who has no pets")
            void findByOwnerReturnsNoRowsWhenOwnerWithNoPetsPassedIn() {
                Owner owner1 = new Owner();
                owner1.setId(22L);
                Set<Pet> pets = service.findByOwner(owner1);
                assertThat(pets, hasSize(0));
            }
        }

        @DisplayName("When we try to add a new Pet")
        @Nested
        class AddPetsTest {
            @Test
            @DisplayName("Then the Pet is added to the list")
            void save() {
                Pet pet = new Pet();
                pet.setId(12L);
                pet.setName("Rocky12");
                service.save(pet);
                Set<Pet> pets = service.findAll();
                assertThat(pets, hasSize(4));
                assertThat(pets.contains(pet), is(true));
            }

        }
        @DisplayName("When we try to delete Pets")
        @Nested
        class DeletePetTest {

            @Test
            @DisplayName("Then we can delete Pet using an existing Pet object")
            void deleteAnExistingPetObject() {
                service.delete(pet2);
                pets = service.findAll();
                assertThat(pets, hasSize(2));
                assertThat(pets.contains(pet2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Pet using a Pet that doesn't exist")
            void deleteANonExistentPetObjectHasNoImpact() {
                Pet pet = new Pet();
                pet.setId(100L);
                pet.setName("test");
                service.delete(pet);
                pets = service.findAll();
                assertThat(pets, hasSize(3));
                assertThat(pets.contains(pet), is(false));
            }

            @Test
            @DisplayName("Then we can delete a Pet using an existing Id")
            void deleteByExistingId() {
                service.deleteById(pet3.getId());
                pets = service.findAll();
                assertThat(pets, hasSize(2));
                assertThat(pets.contains(pet3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Pet using an Id that doesn't exist")
            void deleteByNonExistentId() {
                service.deleteById(101L);
                pets = service.findAll();
                assertThat(pets, hasSize(3));
            }
        }
    }

    //
//    @BeforeEach
//    void setUp() {
//        service = new PetMapService();
//    }
//
//
//
//    @Test
//    void findByOwner() {
//        setUpPets();
//        assertThat(service.findAll(), hasSize(3));
//        Owner owner = new Owner();
//        owner.setId(11L);
//        Set<Pet> pets = service.findByOwner(owner);
//        assertThat(pets, hasSize(1));
//
//
//        pets = service.findByOwner(owner);
//        assertThat(service.findAll(), hasSize(1));
//    }
//
}