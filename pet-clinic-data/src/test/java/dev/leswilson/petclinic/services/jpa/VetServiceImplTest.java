package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.repositories.VetRepository;
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
class VetServiceImplTest {

    @Mock
    private VetRepository repository;
    @InjectMocks
    private VetServiceImpl service;

    private static final Long VET_ID = 123L;
    private static final String LAST_NAME = "Smith";
    private Vet vet;
    private Set<Vet> vetSet;

    @BeforeEach
    void setup() {
        vet = new Vet();
        vet.setId(VET_ID);
        vet.setFirstName("Archie");
        vet.setLastName(LAST_NAME);
        vet.setSpecialities(new ArrayList<>());
        vetSet = new HashSet<>();
        vetSet.add(vet);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(vetSet);
        Set<Vet> vets = service.findAll();
        assertThat(vets, hasSize(1));
        verify(repository).findAll();
    }

    @Test
    void delete() {
        service.delete(vet);
        verify(repository).delete(vet);
    }

    @Test
    void saveValidVet() {
        when(repository.save(any())).thenReturn(vet);
        Vet saved = service.save(new Vet());
        assertThat(saved, is(vet));
        verify(repository).save(any());
    }

    @Test
    void saveNullVet() {
        Vet saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(VET_ID);
        verify(repository).deleteById(VET_ID);
    }

    @Test
    void findById() {
        when(repository.findById(VET_ID)).thenReturn(Optional.of(vet));

        Vet vetFound = service.findById(VET_ID);
        assertThat(vetFound, is(notNullValue()));
        assertThat(vetFound, is(vetFound));
        assertThat(vetFound.getId(), is(VET_ID));
        // Check findById is called once
        verify(repository).findById(VET_ID);
    }

    @Test
    void findAllByOwner() {
        Speciality speciality = new Speciality();
        when(repository.findAllBySpecialities(speciality)).thenReturn(Collections.singletonList(vet));

        List<Vet> list = service.findBySpeciality(speciality);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0), is(vet));
        assertThat(list.get(0).getId(), is(VET_ID));
        // Check method called once
        verify(repository).findAllBySpecialities(speciality);
    }

    @Test
    void findByLastName() {
        when(repository.findByLastName(LAST_NAME)).thenReturn(vet);

        Vet smith = service.findByLastName(LAST_NAME);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(VET_ID));
        // ** INFO ** Next two lines equivalent - if no times, default is one
        verify(repository).findByLastName(LAST_NAME);
        verify(repository, times(1)).findByLastName(LAST_NAME);
    }

    @Test
    void findAllByLastNameLike() {
        when(repository.findAllByLastNameLike(LAST_NAME)).thenReturn(Collections.singletonList(vet));

        List<Vet> list = service.findAllByLastNameLike(LAST_NAME);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0).getId(), is(VET_ID));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findAllByLastNameLike(LAST_NAME);
        verify(repository, times(1)).findAllByLastNameLike(LAST_NAME);
    }

}