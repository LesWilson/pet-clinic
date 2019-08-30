package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.repositories.OwnerRepository;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    private Owner owner;
    private static final String LAST_NAME = "Smith";
    private static final Long OWNER_ID = 123L;

    @Mock
    private OwnerRepository repository;
    @InjectMocks
    private OwnerServiceImpl service;

    @BeforeEach
    void setup() {
        owner = new Owner();
        owner.setId(OWNER_ID);
        owner.setFirstName("Paul");
        owner.setLastName(LAST_NAME);
    }
    
    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);
        when(repository.findAll()).thenReturn(owners);
        Set<Owner> ownerSet = service.findAll();
        assertThat(ownerSet, hasSize(1));
    }

    @Test
    void delete() {
        service.delete(owner);

        verify(repository).delete(owner);
    }

    @Test
    void saveValidOwner() {
        when(repository.save(any())).thenReturn(owner);
        Owner saved = service.save(new Owner());
        assertThat(saved, is(notNullValue()));
        verify(repository).save(any());
    }

    @Test
    void saveNullOwner() {
        Owner saved = service.save(null);
        assertThat(saved, is(nullValue()));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);

        verify(repository).deleteById(OWNER_ID);
    }

    @Test
    void findById() {
        when(repository.findById(OWNER_ID)).thenReturn(Optional.of(owner));

        Owner smith = service.findById(OWNER_ID);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(OWNER_ID));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findById(OWNER_ID);
        verify(repository, times(1)).findById(OWNER_ID);
    }

    @Test
    void findByLastName() {
        when(repository.findByLastName(LAST_NAME)).thenReturn(owner);

        Owner smith = service.findByLastName(LAST_NAME);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(OWNER_ID));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findByLastName(LAST_NAME);
        verify(repository, times(1)).findByLastName(LAST_NAME);
    }

    @Test
    void findAllByLastNameLike() {
        when(repository.findAllByLastNameLike(LAST_NAME)).thenReturn(Collections.singletonList(owner));

        List<Owner> list = service.findAllByLastNameLike(LAST_NAME);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0).getId(), is(OWNER_ID));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findAllByLastNameLike(LAST_NAME);
        verify(repository, times(1)).findAllByLastNameLike(LAST_NAME);
    }
}