package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class OwnerMapServiceTest {

    OwnerMapService service;

    Owner owner1, owner2, owner3;
    Owner owner;
    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        service = new OwnerMapService();
    }

    @DisplayName("Given we have a list of Owners")
    @Nested
    class ExistingOwnerTest {
        @BeforeEach
        void setUp() {
            owner1 = new Owner();
            owner1.setId(1L);
            owner1.setFirstName("Rocky");
            owner1.setLastName("Balboa");
            service.save(owner1);

            owner2 = new Owner();
            owner2.setId(2L);
            owner2.setFirstName("Rocky2");
            owner2.setLastName("Balboa2");
            service.save(owner2);

            owner3 = new Owner();
            owner3.setId(3L);
            owner3.setFirstName("Rocky3");
            owner3.setLastName("Balboa3");
            service.save(owner3);

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
                Owner owner = service.findById(1L);
                assertThat(owner, is(notNullValue()));
                assertThat(owner, is(owner1));
                assertThat(owner.getId(), is(1L));
                assertThat(owner.getFirstName(), is(owner1.getFirstName()));
            }

            @Test
            @DisplayName("Then we do not find a Owner using an Id that doesn't exist")
            void findByInvalidId() {
                Owner owner = service.findById(11L);
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
                Owner owner1 = new Owner();
                owner1.setId(22L);
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
                Owner owner = new Owner();
                owner.setId(12L);
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
                service.delete(owner2);
                owners = service.findAll();
                assertThat(owners, hasSize(2));
                assertThat(owners.contains(owner2), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Owner using a Owner that doesn't exist")
            void deleteANonExistentOwnerObjectHasNoImpact() {
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
                service.deleteById(owner3.getId());
                owners = service.findAll();
                assertThat(owners, hasSize(2));
                assertThat(owners.contains(owner3), is(false));
            }

            @Test
            @DisplayName("Then we cannot delete a Owner using an Id that doesn't exist")
            void deleteByNonExistentId() {
                service.deleteById(101L);
                owners = service.findAll();
                assertThat(owners, hasSize(3));
            }
        }
    }
}