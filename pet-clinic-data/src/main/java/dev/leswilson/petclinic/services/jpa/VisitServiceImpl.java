package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import dev.leswilson.petclinic.repositories.VisitRepository;
import dev.leswilson.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit t) {
        visitRepository.delete(t);
    }

    @Override
    public Visit save(Visit visit) {
        // add logic to iterate through pets and save any that don't exist
        if(visit != null) {
            return visitRepository.save(visit);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Visit> findByPet(Pet pet) {
        return visitRepository.findAllByPet(pet);
    }

    @Override
    public List<Visit> findByOwner(Owner owner) {
        return visitRepository.findAllByPetOwner(owner);
    }
}
