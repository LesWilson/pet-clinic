package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.repositories.SpecialityRepository;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialityServiceImplTest {

    private Speciality speciality;
    private final Long specialityId = 123L;

    @Mock
    private SpecialityRepository repository;
    @InjectMocks
    private SpecialityServiceImpl service;

    @BeforeEach
    void setup() {
        speciality = new Speciality();
        speciality.setId(specialityId);
        speciality.setDescription("Dentistry");
    }
    
    @Test
    void findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(speciality);
        when(repository.findAll()).thenReturn(specialities);
        Set<Speciality> specialitySet = service.findAll();
        assertThat(specialitySet, hasSize(1));
    }

    @Test
    void delete() {
        service.delete(speciality);

        verify(repository).delete(speciality);
    }

    @Test
    void saveValidSpeciality() {
        when(repository.save(any())).thenReturn(speciality);
        Speciality saved = service.save(new Speciality());
        assertThat(saved, is(notNullValue()));
        verify(repository).save(any());
    }

    @Test
    void saveNullSpeciality() {
        Speciality saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(specialityId);

        verify(repository).deleteById(specialityId);
    }

    @Test
    void findById() {
        when(repository.findById(specialityId)).thenReturn(Optional.of(speciality));

        Speciality smith = service.findById(specialityId);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(specialityId));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findById(specialityId);
        verify(repository, times(1)).findById(specialityId);
    }

}