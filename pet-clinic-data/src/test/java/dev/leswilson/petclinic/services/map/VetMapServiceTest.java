package dev.leswilson.petclinic.services.map;

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
    private Set<Vet> vets;

    @BeforeEach
    void setUp() {
        service = new VetMapService();
    }

    @DisplayName("Given we have a list of Vets")
    @Nested
    class ExistingVetTest {
        @BeforeEach
        void setUp() {
            vet1 = new Vet();
            vet1.setId(1L);
            vet1.setFirstName("Rocky");
            vet1.setLastName("Balboa");
            service.save(vet1);

            vet2 = new Vet();
            vet2.setId(2L);
            vet2.setFirstName("Rocky2");
            vet2.setLastName("Balboa2");
            service.save(vet2);

            vet3 = new Vet();
            vet3.setId(3L);
            vet3.setFirstName("Rocky3");
            vet3.setLastName("Balboa3");
            service.save(vet3);

            vets = service.findAll();
        }

        @DisplayName("When we search for Vets")
        @Nested
        class FindVetTest {

            @Test
            @DisplayName("Then we can find all Vets")
            void findAll() {
                assertThat(vets, hasSize(3));
                assertThat(vets, hasItems(vet1, vet3, vet2));
            }

            @Test
            @DisplayName("Then we can find a Vet using an existing Id")
            void findByValidId() {
                Vet vet = service.findById(1L);
                assertThat(vet, is(notNullValue()));
                assertThat(vet, is(vet1));
                assertThat(vet.getId(), is(1L));
                assertThat(vet.getFirstName(), is(vet1.getFirstName()));
            }

            @Test
            @DisplayName("Then we do not find a Vet using an Id that doesn't exist")
            void findByInvalidId() {
                Vet vet = service.findById(11L);
                assertThat(vet, is(nullValue()));
            }

        }

        @DisplayName("When we try to add a new Vet")
        @Nested
        class AddVetsTest {
            @Test
            @DisplayName("Then the Vet is added to the list")
            void save() {
                assertThat(vets, hasSize(3));
                Vet vet = new Vet();
                vet.setId(12L);
                vet.setFirstName("Rocky12");
                service.save(vet);
                Set<Vet> vets = service.findAll();
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
                assertThat(vets, hasSize(3));
                service.delete(vet2);
                vets = service.findAll();
                assertThat(vets, hasSize(2));
                assertThat(vets.contains(vet2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Vet using a Vet that doesn't exist")
            void deleteANonExistentVetObjectHasNoImpact() {
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
                assertThat(vets, hasSize(3));
                service.deleteById(vet3.getId());
                vets = service.findAll();
                assertThat(vets, hasSize(2));
                assertThat(vets.contains(vet3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Vet using an Id that doesn't exist")
            void deleteByNonExistentId() {
                assertThat(vets, hasSize(3));
                service.deleteById(101L);
                vets = service.findAll();
                assertThat(vets, hasSize(3));
            }
        }
    }
}