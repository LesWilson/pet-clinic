package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class PetTypeMapServiceTest {

    private PetTypeMapService service;

    private PetType petType1;
    private PetType petType2;
    private PetType petType3;
    private Set<PetType> petTypeSet;

    @BeforeEach
    void setUp() {
        service = new PetTypeMapService();
    }

    @DisplayName("Given we have a list of PetTypes")
    @Nested
    class ExistingPetTypeTest {
        @BeforeEach
        void setUp() {
            petType1 = new PetType();
            petType1.setName("Dog");
            petType1 = service.save(petType1);

            petType2 = new PetType();
            petType2.setName("Cat");
            petType2 = service.save(petType2);

            petType3 = new PetType();
            petType3.setName("Parrot");
            petType3 = service.save(petType3);

            petTypeSet = service.findAll();
        }

        @DisplayName("When we search for PetTypes")
        @Nested
        class FindPetTypeTest {

            @Test
            @DisplayName("Then we can find all PetTypes")
            void findAll() {
                // findAll has been called in setUp, so we are just confirming it works here
                assertThat(petTypeSet, hasSize(3));
                assertThat(petTypeSet, hasItems(petType1, petType3, petType2));
            }

            @Test
            @DisplayName("Then we can find a PetType using an existing Id")
            void findByValidId() {
                PetType petType = service.findById(petType1.getId());
                assertThat(petType, is(notNullValue()));
                assertThat(petType, is(petType1));
                assertThat(petType.getId(), is(petType1.getId()));
                assertThat(petType.getName(), is(petType1.getName()));
            }

            @Test
            @DisplayName("Then we do not find a PetType using an Id that doesn't exist")
            void findByInvalidId() {
                PetType petType = service.findById(111L);
                assertThat(petType, is(nullValue()));
            }

        }

        @DisplayName("When we try to add a new PetType")
        @Nested
        class AddPetTypesTest {
            @Test
            @DisplayName("Then the PetType is added to the list")
            void save() {
                assertThat(petTypeSet, hasSize(3));
                PetType petType = new PetType();
                petType.setName("Rocky12");
                service.save(petType);
                Set<PetType> petTypes = service.findAll();
                assertThat(petTypes, hasSize(4));
                assertThat(petTypes.contains(petType), is(true));
            }

        }
        @DisplayName("When we try to delete PetTypes")
        @Nested
        class DeletePetTypeTest {

            @Test
            @DisplayName("Then we can delete PetType using an existing PetType object")
            void deleteAnExistingPetTypeObject() {
                assertThat(petTypeSet, hasSize(3));
                service.delete(petType2);
                petTypeSet = service.findAll();
                assertThat(petTypeSet, hasSize(2));
                assertThat(petTypeSet.contains(petType2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a PetType using a PetType that doesn't exist")
            void deleteANonExistentPetTypeObjectHasNoImpact() {
                assertThat(petTypeSet, hasSize(3));
                PetType petType = new PetType();
                petType.setId(100L);
                petType.setName("test");
                service.delete(petType);
                petTypeSet = service.findAll();
                assertThat(petTypeSet, hasSize(3));
                assertThat(petTypeSet.contains(petType), is(false));
            }

            @Test
            @DisplayName("Then we can delete a PetType using an existing Id")
            void deleteByExistingId() {
                assertThat(petTypeSet, hasSize(3));
                service.deleteById(petType3.getId());
                petTypeSet = service.findAll();
                assertThat(petTypeSet, hasSize(2));
                assertThat(petTypeSet.contains(petType3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a PetType using an Id that doesn't exist")
            void deleteByNonExistentId() {
                assertThat(petTypeSet, hasSize(3));
                service.deleteById(101L);
                petTypeSet = service.findAll();
                assertThat(petTypeSet, hasSize(3));
            }
        }
    }
}