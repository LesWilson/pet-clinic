package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet t) {
        super.delete(t);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findBySpeciality(Speciality speciality) {

        return this.findAll()
                .stream()
                .filter(vet -> vet.getSpecialities() != null && vet.getSpecialities().contains(speciality))
                .collect(toSet());
    }
}
