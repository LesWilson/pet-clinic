package dev.leswilson.petclinic.services.jpa;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.repositories.SpecialityRepository;
import dev.leswilson.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(Speciality t) {
        specialityRepository.delete(t);
    }

    @Override
    public Speciality save(Speciality speciality) {
        // add logic to iterate through pets and save any that don't exist
        if(speciality != null) {
            return specialityRepository.save(speciality);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

}
