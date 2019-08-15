package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.services.OwnerService;
import dev.leswilson.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetService petService;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PetService petService) {
        this.ownerRepository = ownerRepository;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void delete(Owner t) {
        ownerRepository.delete(t);
    }

    @Override
    public Owner save(Owner owner) {
        // add logic to iterate through pets and save any that don't exist
        if(owner != null) {
            return ownerRepository.save(owner);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

}
