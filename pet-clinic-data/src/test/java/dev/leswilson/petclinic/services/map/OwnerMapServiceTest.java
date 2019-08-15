package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.services.PetService;
import dev.leswilson.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class OwnerMapServiceTest {

    private OwnerMapService service;

    private PetTypeService petTypeService;
    private PetService petService;

    private Owner owner1, owner2, owner3;
    private Set<Owner> owners;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        service = new OwnerMapService(petService);
    }

    @DisplayName("Given we have a list of Owners")
    @Nested
    class ExistingOwnerTest {
        @BeforeEach
        void setUp() {
            owner1 = new Owner();
            owner1.setFirstName("Rocky");
            owner1.setLastName("Balboa");
            owner1 = service.save(owner1);

            owner2 = new Owner();
            owner2.setFirstName("Rocky2");
            owner2.setLastName("Balboa2");
            owner2 = service.save(owner2);

            owner3 = new Owner();
            owner3.setFirstName("Rocky3");
            owner3.setLastName("Balboa3");
            owner3 = service.save(owner3);

            owners = service.findAll();
        }

        @DisplayName("When we search for Owners")
        @Nested
        class FindOwnerTest {

            @Test
            @DisplayName("Then we can find all Owners")
            void findAll() {
                assertThat(owners, hasSize(3));
                assertThat(owners, hasItems(owner1, owner3, owner2));
            }

            @Test
            @DisplayName("Then we can find a Owner using an existing Id")
            void findByValidId() {
                Owner owner = service.findById(owner1.getId());
                assertThat(owner, is(notNullValue()));
                assertThat(owner, is(owner1));
                assertThat(owner.getId(), is(owner1.getId()));
                assertThat(owner.getFirstName(), is(owner1.getFirstName()));
            }

            @Test
            @DisplayName("Then we do not find a Owner using an Id that doesn't exist")
            void findByInvalidId() {
                Owner owner = service.findById(111L);
                assertThat(owner, is(nullValue()));
            }

            @Test
            @DisplayName("Then we can find an Owner by last name")
            void findByOwnerReturnsRowsWhenExistingLastNamePassedIn() {
                Owner owner = service.findByLastName("Balboa2");
                assertThat(owner, is(notNullValue()));
                assertThat(owner, is(owner2));
            }

            @Test
            @DisplayName("Then we cannot find an Owner by last name that doesn't exist")
            void findByOwnerReturnsNoRowsWhenNonExistentLastNamePassedIn() {
                Owner owner = service.findByLastName("Smith");
                assertThat(owner, is(nullValue()));
            }
        }

        @DisplayName("When we try to add a new Owner")
        @Nested
        class AddOwnersTest {
            @Test
            @DisplayName("Then the Owner is added to the list")
            void save() {
                assertThat(owners, hasSize(3));
                Owner owner = new Owner();
                owner.setFirstName("Rocky12");
                service.save(owner);
                Set<Owner> owners = service.findAll();
                assertThat(owners, hasSize(4));
                assertThat(owners.contains(owner), is(true));
            }
        }
        @DisplayName("When we try to delete Owners")
        @Nested
        class DeleteOwnerTest {

            @Test
            @DisplayName("Then we can delete Owner using an existing Owner object")
            void deleteAnExistingOwnerObject() {
                assertThat(owners, hasSize(3));
                service.delete(owner2);
                owners = service.findAll();
                assertThat(owners, hasSize(2));
                assertThat(owners.contains(owner2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Owner using a Owner that doesn't exist")
            void deleteANonExistentOwnerObjectHasNoImpact() {
                assertThat(owners, hasSize(3));
                Owner owner = new Owner();
                owner.setId(100L);
                owner.setFirstName("test");
                service.delete(owner);
                owners = service.findAll();
                assertThat(owners, hasSize(3));
                assertThat(owners.contains(owner), is(false));
            }

            @Test
            @DisplayName("Then we can delete a Owner using an existing Id")
            void deleteByExistingId() {
                assertThat(owners, hasSize(3));
                service.deleteById(owner3.getId());
                owners = service.findAll();
                assertThat(owners, hasSize(2));
                assertThat(owners.contains(owner3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Owner using an Id that doesn't exist")
            void deleteByNonExistentId() {
                assertThat(owners, hasSize(3));
                service.deleteById(101L);
                owners = service.findAll();
                assertThat(owners, hasSize(3));
            }
        }

        @DisplayName("When we add a New Pet to an Owner")
        @Nested
        class AddNewPetsToOwnersTest {
            @Test
            @DisplayName("Then a new pet id is created when the Owner is saved")
            void addNewPetToOwner() {
                Owner owner = new Owner();
                owner.setFirstName("Rocky12");
                Pet pet = new Pet();
                pet.setName("Roxy");
                owner.getPets().add(pet);
                assertThat(((Pet)owner.getPets().toArray()[0]).getId(), is(nullValue()));
                service.save(owner);
                assertThat(((Pet)owner.getPets().toArray()[0]).getId(), is(notNullValue()));
            }
        }

        @DisplayName("When we add an Existing Pet to an Owner")
        @Nested
        class AddExistingPetsToOwnersTest {
            @Test
            @DisplayName("Then the pet id is unchanged when the Owner is saved")
            void addNewPetToOwner() {
                Owner owner = new Owner();
                owner.setFirstName("Rocky12");
                Pet pet = new Pet();
                pet.setId(123L);
                pet.setName("Roxy");
                owner.getPets().add(pet);
                assertThat(((Pet)owner.getPets().toArray()[0]).getId(), is(123L));
                service.save(owner);
                assertThat(((Pet)owner.getPets().toArray()[0]).getId(), is(123L));
            }
        }

        @DisplayName("When we add New and Existing Pets to an Owner")
        @Nested
        class AddNewAndExistingPetsToOwnersTest {
            @Test
            @DisplayName("Then the new pet gets an id and the existing pet id is unchanged when the Owner is saved")
            void addNewAndExistingPetsToOwner() {
                Owner owner = new Owner();
                owner.setFirstName("Rocky12");
                Pet pet = new Pet();
                pet.setId(123L);
                pet.setName("Roxy");
                owner.getPets().add(pet);

                Pet pet2 = new Pet();
                pet2.setName("Moxy");
                owner.getPets().add(pet2);

                assertThat(owner.getPets(), hasSize(2));

                // Check we have one new and one existing pet
                assertThat(owner.getPets().stream().filter(Pet::isNew).count(), is(1L));
                assertThat(owner.getPets().stream().filter(p -> !p.isNew()).count(), is(1L));

                service.save(owner);

                // Now we should no longer have any new pets and Roxy should still be id 123
                Set<Pet> pets = owner.getPets();
                assertThat(pets.stream().filter(Pet::isNew).count(), is(0L));
                assertThat(pets.stream().filter(p -> !p.isNew()).count(), is(2L));
                assertThat(pets.stream().filter(p -> "Roxy".equals(p.getName()) && p.getId().equals(123L)).count(), is(1L));
            }
        }

    }
}