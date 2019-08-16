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
    private final String lastName = "Smith";
    private final Long ownerId = 123L;

    @Mock
    private OwnerRepository repository;
    @InjectMocks
    private OwnerServiceImpl service;

    @BeforeEach
    public void setup() {
        owner = new Owner();
        owner.setId(ownerId);
        owner.setFirstName("Paul");
        owner.setLastName(lastName);
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
        service.deleteById(ownerId);

        verify(repository).deleteById(ownerId);
    }

    @Test
    void findById() {
        when(repository.findById(ownerId)).thenReturn(Optional.of(owner));

        Owner smith = service.findById(ownerId);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(ownerId));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findById(ownerId);
        verify(repository, times(1)).findById(ownerId);
    }

    @Test
    void findByLastName() {
        when(repository.findByLastName(lastName)).thenReturn(owner);

        Owner smith = service.findByLastName(lastName);
        assertThat(smith, is(notNullValue()));
        assertThat(smith.getId(), is(ownerId));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findByLastName(lastName);
        verify(repository, times(1)).findByLastName(lastName);
    }

    @Test
    void findAllByLastNameLike() {
        when(repository.findAllByLastNameLike(lastName)).thenReturn(Arrays.asList(owner));

        List<Owner> list = service.findAllByLastNameLike(lastName);
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(1));
        assertThat(list.get(0).getId(), is(ownerId));
        // Next two lines equivalent - if no times, default is one
        verify(repository).findAllByLastNameLike(lastName);
        verify(repository, times(1)).findAllByLastNameLike(lastName);
    }
}