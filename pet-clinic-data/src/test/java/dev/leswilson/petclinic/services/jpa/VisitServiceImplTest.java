package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import dev.leswilson.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceImplTest {

    @Mock
    private VisitRepository repository;
    @InjectMocks
    private VisitServiceImpl service;

    private static final Long VISIT_ID = 123L;
    private Visit visit;
    private Set<Visit> visitSet;

    @BeforeEach
    void setup() {
        visit = new Visit();
        visit.setId(VISIT_ID);
        visit.setDate(LocalDateTime.now());
        visit.setDescription("Visit 1");
        visitSet = new HashSet<>();
        visitSet.add(visit);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(visitSet);
        Set<Visit> visits = service.findAll();
        assertThat(visits, hasSize(1));
        verify(repository).findAll();
    }

    @Test
    void delete() {
        service.delete(visit);
        verify(repository).delete(visit);
    }

    @Test
    void saveValidVisit() {
        when(repository.save(any())).thenReturn(visit);
        Visit saved = service.save(new Visit());
        assertThat(saved, is(visit));
        verify(repository).save(any());
    }

    @Test
    void saveNullVisit() {
        Visit saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(VISIT_ID);
        verify(repository).deleteById(VISIT_ID);
    }

    @Test
    void findById() {
        when(repository.findById(VISIT_ID)).thenReturn(Optional.of(visit));

        Visit visitFound = service.findById(VISIT_ID);
        assertThat(visitFound, is(notNullValue()));
        assertThat(visitFound, is(visitFound));
        assertThat(visitFound.getId(), is(VISIT_ID));
        // Check findById is called once
        verify(repository).findById(VISIT_ID);
    }

    @Test
    void findByPet() {
        Pet pet = new Pet();
        when(repository.findAllByPet(pet)).thenReturn(Collections.singletonList(visit));

        List<Visit> matchingVisits = service.findByPet(pet);
        assertThat(matchingVisits, hasSize(1));
        assertThat(matchingVisits.get(0), is(visit));
        // ** INFO ** Next two lines equivalent - if no times, default is one
        verify(repository).findAllByPet(pet);
        verify(repository, times(1)).findAllByPet(pet);
    }

    @Test
    void findAllByOwner() {
        Owner owner = new Owner();
        when(repository.findAllByPetOwner(owner)).thenReturn(Collections.singletonList(visit));

        List<Visit> list = service.findByOwner(owner);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0), is(visit));
        assertThat(list.get(0).getId(), is(VISIT_ID));
        // Check method called once
        verify(repository).findAllByPetOwner(owner);
    }
}