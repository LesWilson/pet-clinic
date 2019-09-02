package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.PetType;
import dev.leswilson.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetTypeServiceImplTest {

    @Mock
    private PetTypeRepository repository;
    @InjectMocks
    private PetTypeServiceImpl service;

    private static final Long PET_TYPE_ID = 123L;
    private PetType petType;
    private Set<PetType> petTypeSet;

    @BeforeEach
    void setup() {
        petType = new PetType();
        petType.setId(PET_TYPE_ID);
        petType.setName("Archie");
        petTypeSet = new HashSet<>();
        petTypeSet.add(petType);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(petTypeSet);
        Set<PetType> pets = service.findAll();
        assertThat(pets, hasSize(1));
        verify(repository).findAll();
    }

    @Test
    void delete() {
        service.delete(petType);
        verify(repository).delete(petType);
    }

    @Test
    void saveValidPet() {
        when(repository.save(any())).thenReturn(petType);
        PetType saved = service.save(new PetType());
        assertThat(saved, is(petType));
        verify(repository).save(any());
    }

    @Test
    void saveNullPet() {
        PetType saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(PET_TYPE_ID);
        verify(repository).deleteById(PET_TYPE_ID);
    }

    @Test
    void findById() {
        when(repository.findById(PET_TYPE_ID)).thenReturn(Optional.of(petType));

        PetType petTypeFound = service.findById(PET_TYPE_ID);
        assertThat(petTypeFound, is(notNullValue()));
        assertThat(petTypeFound, is(petTypeFound));
        assertThat(petTypeFound.getId(), is(PET_TYPE_ID));
        // Check findById is called once
        verify(repository).findById(PET_TYPE_ID);
    }
}