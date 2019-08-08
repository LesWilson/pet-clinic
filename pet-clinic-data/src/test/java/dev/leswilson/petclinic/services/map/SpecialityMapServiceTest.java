package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class SpecialityMapServiceTest {

    private SpecialityMapService service;

    private Speciality speciality1, speciality2, speciality3;
    private Set<Speciality> specialities;

    @BeforeEach
    void setUp() {
        service = new SpecialityMapService();
    }

    @DisplayName("Given we have a list of Specialities")
    @Nested
    class ExistingSpecialityTest {
        @BeforeEach
        void setUp() {
            speciality1 = new Speciality();
            speciality1.setDescription("Rocky");
            speciality1 = service.save(speciality1);

            speciality2 = new Speciality();
            speciality2.setDescription("Rocky2");
            speciality2 = service.save(speciality2);

            speciality3 = new Speciality();
            speciality3.setDescription("Rocky3");
            speciality3 = service.save(speciality3);

            specialities = service.findAll();
        }

        @DisplayName("When we search for Specialities")
        @Nested
        class FindSpecialityTest {

            @Test
            @DisplayName("Then we can find all Specialities")
            void findAll() {
                assertThat(specialities, hasSize(3));
                assertThat(specialities, hasItems(speciality1, speciality3, speciality2));
            }

            @Test
            @DisplayName("Then we can find a Speciality using an existing Id")
            void findByValidId() {
                Speciality speciality = service.findById(speciality1.getId());
                assertThat(speciality, is(notNullValue()));
                assertThat(speciality, is(speciality1));
                assertThat(speciality.getId(), is(speciality1.getId()));
                assertThat(speciality.getDescription(), is(speciality1.getDescription()));
            }

            @Test
            @DisplayName("Then we do not find a Speciality using an Id that doesn't exist")
            void findByInvalidId() {
                Speciality speciality = service.findById(11L);
                assertThat(speciality, is(nullValue()));
            }
        }

        @DisplayName("When we try to add a new Speciality")
        @Nested
        class AddSpecialitiesTest {
            @Test
            @DisplayName("Then the Speciality is added to the list")
            void save() {
                Speciality speciality = new Speciality();
                speciality.setDescription("Rocky12");
                speciality = service.save(speciality);
                specialities = service.findAll();
                assertThat(specialities, hasSize(4));
                assertThat(specialities.contains(speciality), is(true));
            }

        }
        @DisplayName("When we try to delete Specialities")
        @Nested
        class DeleteSpecialityTest {

            @Test
            @DisplayName("Then we can delete Speciality using an existing Speciality object")
            void deleteAnExistingSpecialityObject() {
                assertThat(specialities, hasSize(3));
                service.delete(speciality2);
                specialities = service.findAll();
                assertThat(specialities, hasSize(2));
                assertThat(specialities.contains(speciality2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Speciality using a Speciality that doesn't exist")
            void deleteANonExistentSpecialityObjectHasNoImpact() {
                assertThat(specialities, hasSize(3));
                Speciality speciality = new Speciality();
                speciality.setId(100L);
                speciality.setDescription("test");
                service.delete(speciality);
                specialities = service.findAll();
                assertThat(specialities, hasSize(3));
                assertThat(specialities.contains(speciality), is(false));
            }

            @Test
            @DisplayName("Then we can delete a Speciality using an existing Id")
            void deleteByExistingId() {
                assertThat(specialities, hasSize(3));
                service.deleteById(speciality3.getId());
                specialities = service.findAll();
                assertThat(specialities, hasSize(2));
                assertThat(specialities.contains(speciality3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Speciality using an Id that doesn't exist")
            void deleteByNonExistentId() {
                assertThat(specialities, hasSize(3));
                service.deleteById(101L);
                specialities = service.findAll();
                assertThat(specialities, hasSize(3));
            }
        }
    }
}