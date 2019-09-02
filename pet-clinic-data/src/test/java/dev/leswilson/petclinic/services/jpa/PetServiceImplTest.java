package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetRepository repository;
    @InjectMocks
    private PetServiceImpl service;

    private static final Long PET_ID = 123L;
    private Pet pet;
    private Set<Pet> petSet;

    @BeforeEach
    void setup() {
        pet = new Pet();
        pet.setId(PET_ID);
        pet.setName("Archie");
        petSet = new HashSet<>();
        petSet.add(pet);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(petSet);
        Set<Pet> pets = service.findAll();
        assertThat(pets, hasSize(1));
        verify(repository).findAll();
    }

    @Test
    void delete() {
        service.delete(pet);
        verify(repository).delete(pet);
    }

    @Test
    void saveValidPet() {
        when(repository.save(any())).thenReturn(pet);
        Pet saved = service.save(new Pet());
        assertThat(saved, is(pet));
        verify(repository).save(any());
    }

    @Test
    void saveNullPet() {
        Pet saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(PET_ID);
        verify(repository).deleteById(PET_ID);
    }

    @Test
    void findById() {
        when(repository.findById(PET_ID)).thenReturn(Optional.of(pet));

        Pet petFound = service.findById(PET_ID);
        assertThat(petFound, is(notNullValue()));
        assertThat(petFound, is(petFound));
        assertThat(petFound.getId(), is(PET_ID));
        // Check findById is called once
        verify(repository).findById(PET_ID);
    }

    @Test
    void findAllByOwner() {
        Owner owner = new Owner();
        when(repository.findAllByOwner(owner)).thenReturn(Collections.singletonList(pet));

        List<Pet> list = service.findByOwner(owner);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0), is(pet));
        assertThat(list.get(0).getId(), is(PET_ID));
        // Check method called once
        verify(repository).findAllByOwner(owner);
    }
}