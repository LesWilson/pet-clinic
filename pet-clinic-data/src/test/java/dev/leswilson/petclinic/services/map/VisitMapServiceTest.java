package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class VisitMapServiceTest {

    private VisitMapService service;

    private Visit visit1;
    private Visit visit2;
    private Visit visit3;
    private Pet pet1;
    private Pet pet2;
    private Pet pet3;
    private Owner owner1;
    private Owner owner2;
    private Set<Visit> visitSet;

    @BeforeEach
    void setUp() {
        service = new VisitMapService();
    }

    @DisplayName("Given we have a list of Visits")
    @Nested
    class ExistingVisitTest {
        @BeforeEach
        void setUp() {
            owner1 = new Owner();
            owner1.setId(1L);
            owner1.setFirstName("Fred");
            owner1.setLastName("Smith");

            pet1 = new Pet();
            pet1.setId(1L);
            pet1.setName("Archie");
            pet1.setOwner(owner1);

            owner1.getPets().add(pet1);

            owner2 = new Owner();
            owner2.setId(2L);
            owner2.setFirstName("John");
            owner2.setLastName("Jones");

            pet2 = new Pet();
            pet2.setId(2L);
            pet2.setName("Rocky");
            pet2.setOwner(owner2);

            pet3 = new Pet();
            pet3.setId(3L);
            pet3.setName("Mr T");
            pet3.setOwner(owner2);

            owner2.getPets().add(pet2);
            owner2.getPets().add(pet3);

            visit1 = new Visit();
            visit1.setDescription("Archie's checkup");
            visit1.setPet(pet1);
            visit1 = service.save(visit1);

            visit2 = new Visit();
            visit2.setDescription("Archie getting teeth cleaned");
            visit2.setPet(pet1);
            visit2 = service.save(visit2);

            visit3 = new Visit();
            visit3.setDescription("Rocky getting vaccinations");
            visit3.setPet(pet2);
            visit3 = service.save(visit3);

            visitSet = service.findAll();
        }

        @DisplayName("When we search for Visits")
        @Nested
        class FindVisitTest {

            @Test
            @DisplayName("Then we can find all Visits")
            void findAll() {
                assertThat(visitSet, hasSize(3));
                assertThat(visitSet, hasItems(visit1, visit3, visit2));
            }

            @Test
            @DisplayName("Then we can find a Visit using an existing Id")
            void findByValidId() {
                Visit visit = service.findById(visit1.getId());
                assertThat(visit, is(notNullValue()));
                assertThat(visit, is(visit1));
                assertThat(visit.getId(), is(visit1.getId()));
                assertThat(visit.getDescription(), is(visit1.getDescription()));
            }

            @Test
            @DisplayName("Then we do not find a Visit using an Id that doesn't exist")
            void findByInvalidId() {
                Visit visit = service.findById(11L);
                assertThat(visit, is(nullValue()));
            }

            @Test
            @DisplayName("Then we can find Visits by a Pet who has Visits")
            void findByPetReturnsRowsWhenPetWithVisitsPassedIn() {
                List<Visit> visits = service.findByPet(pet1);
                assertThat(visits, hasSize(2));
                assertThat(visits, hasItems(visit1, visit2));
            }

            @Test
            @DisplayName("Then we cannot find a visit using a Pet who has no visits")
            void findByPetReturnsNoRowsWhenPetWithNoVisitsPassedIn() {
                List<Visit> visits = service.findByPet(pet3);
                assertThat(visits, hasSize(0));
            }

            @Test
            @DisplayName("Then we can find Visits by an Owner whose Pets have Visits")
            void findByOwnerReturnsRowsWhenOwnerWithPetWithVisitsPassedIn() {
                List<Visit> visits = service.findByOwner(owner1);
                assertThat(visits, hasSize(2));
                assertThat(visits, hasItems(visit1, visit2));
            }

            @Test
            @DisplayName("Then we cannot find a visit using an Owner whose Pets have no visits")
            void findByOwnerReturnsNoRowsWhenOwnerWithPetsWithNoVisitsPassedIn() {
                Owner owner = new Owner();
                owner.setId(222L);
                List<Visit> visits = service.findByOwner(owner);
                assertThat(visits, hasSize(0));
            }
        }

        @DisplayName("When we try to add a new Visit")
        @Nested
        class AddVisitsTest {
            @Test
            @DisplayName("Then the Visit is added to the list")
            void save() {
                Visit visit = new Visit();
                visit.setDescription("Rocky12");
                visit = service.save(visit);
                visitSet = service.findAll();
                assertThat(visitSet, hasSize(4));
                assertThat(visitSet.contains(visit), is(true));
            }

        }
        @DisplayName("When we try to delete Visits")
        @Nested
        class DeleteVisitTest {

            @Test
            @DisplayName("Then we can delete Visit using an existing Visit object")
            void deleteAnExistingVisitObject() {
                assertThat(visitSet, hasSize(3));
                service.delete(visit2);
                visitSet = service.findAll();
                assertThat(visitSet, hasSize(2));
                assertThat(visitSet.contains(visit2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Visit using a Visit that doesn't exist")
            void deleteANonExistentVisitObjectHasNoImpact() {
                assertThat(visitSet, hasSize(3));
                Visit visit = new Visit();
                visit.setId(100L);
                visit.setDescription("test");
                service.delete(visit);
                visitSet = service.findAll();
                assertThat(visitSet, hasSize(3));
                assertThat(visitSet.contains(visit), is(false));
            }

            @Test
            @DisplayName("Then we can delete a Visit using an existing Id")
            void deleteByExistingId() {
                assertThat(visitSet, hasSize(3));
                service.deleteById(visit3.getId());
                visitSet = service.findAll();
                assertThat(visitSet, hasSize(2));
                assertThat(visitSet.contains(visit3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Visit using an Id that doesn't exist")
            void deleteByNonExistentId() {
                assertThat(visitSet, hasSize(3));
                service.deleteById(101L);
                visitSet = service.findAll();
                assertThat(visitSet, hasSize(3));
            }
        }
    }
}