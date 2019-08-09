package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class VetMapServiceTest {

    private VetMapService service;

    private Vet vet1, vet2, vet3;
    private Speciality speciality1, speciality2;

    @BeforeEach
    void setUp() {
        service = new VetMapService();
    }

    @DisplayName("Given we have a list of Vets")
    @Nested
    class ExistingVetTest {
        @BeforeEach
        void setUp() {
            speciality1 = new Speciality();
            speciality1.setDescription("Spec1");
            speciality1.setId(1L);

            speciality2 = new Speciality();
            speciality2.setDescription("Spec2");
            speciality2.setId(2L);

            vet1 = new Vet();
            vet1.setFirstName("Rocky");
            vet1.setLastName("Balboa");
            vet1.getSpecialities().add(speciality1);
            vet1.getSpecialities().add(speciality2);
            vet1 = service.save(vet1);

            vet2 = new Vet();
            vet2.setFirstName("Rocky2");
            vet2.setLastName("Balboa2");
            vet2.getSpecialities().add(speciality1);
            vet2 = service.save(vet2);

            vet3 = new Vet();
            vet3.setFirstName("Rocky3");
            vet3.setLastName("Balboa3");
            vet3.getSpecialities().add(speciality2);
            vet3 = service.save(vet3);

        }

        @DisplayName("When we search for Vets")
        @Nested
        class FindVetTest {

            @Test
            @DisplayName("Then we can find all Vets")
            void findAll() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                assertThat(vets, hasItems(vet1, vet3, vet2));
            }

            @Test
            @DisplayName("Then we can find a Vet using an existing Id")
            void findByValidId() {
                Vet vet = service.findById(vet1.getId());
                assertThat(vet, is(notNullValue()));
                assertThat(vet, is(vet1));
                assertThat(vet.getId(), is(vet1.getId()));
                assertThat(vet.getFirstName(), is(vet1.getFirstName()));
            }

            @Test
            @DisplayName("Then we do not find a Vet using an Id that doesn't exist")
            void findByInvalidId() {
                Vet vet = service.findById(111L);
                assertThat(vet, is(nullValue()));
            }

            @Test
            @DisplayName("Then we can find Vets who have the required Speciality")
            void findBySpecialityReturnsRowsWhenVetsExistWithSpecialityPassedIn() {
                Set<Vet> vets = service.findBySpeciality(speciality1);
                assertThat(vets, hasSize(2));
                assertThat(vets, hasItems(vet1, vet2));
                vets = service.findBySpeciality(speciality2);
                assertThat(vets, hasSize(2));
                assertThat(vets, hasItems(vet1, vet3));
            }

            @Test
            @DisplayName("Then we cannot find any vets using a Speciality no-one has")
            void findBySpecialityReturnsNoRowsWhenSpecialityWithNoVetsPassedIn() {
                Speciality speciality = new Speciality();
                speciality.setDescription("Spec10");
                speciality.setId(10L);

                Set<Vet> vets = service.findBySpeciality(speciality);
                assertThat(vets, hasSize(0));
            }

        }

        @DisplayName("When we try to add a new Vet")
        @Nested
        class AddVetsTest {
            @Test
            @DisplayName("Then the Vet is added to the list")
            void save() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                Vet vet = new Vet();
                vet.setFirstName("Rocky12");
                service.save(vet);
                vets = service.findAll();
                assertThat(vets, hasSize(4));
                assertThat(vets.contains(vet), is(true));
            }

        }
        @DisplayName("When we try to delete Vets")
        @Nested
        class DeleteVetTest {

            @Test
            @DisplayName("Then we can delete Vet using an existing Vet object")
            void deleteAnExistingVetObject() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                service.delete(vet2);
                vets = service.findAll();
                assertThat(vets, hasSize(2));
                assertThat(vets.contains(vet2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Vet using a Vet that doesn't exist")
            void deleteANonExistentVetObjectHasNoImpact() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                Vet vet = new Vet();
                vet.setId(100L);
                vet.setFirstName("test");
                service.delete(vet);
                vets = service.findAll();
                assertThat(vets, hasSize(3));
                assertThat(vets.contains(vet), is(false));
            }

            @Test
            @DisplayName("Then we can delete a Vet using an existing Id")
            void deleteByExistingId() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                service.deleteById(vet3.getId());
                vets = service.findAll();
                assertThat(vets, hasSize(2));
                assertThat(vets.contains(vet3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Vet using an Id that doesn't exist")
            void deleteByNonExistentId() {
                Set<Vet> vets = service.findAll();
                assertThat(vets, hasSize(3));
                service.deleteById(101L);
                vets = service.findAll();
                assertThat(vets, hasSize(3));
            }
        }
    }
}