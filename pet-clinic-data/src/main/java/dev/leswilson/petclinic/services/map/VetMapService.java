package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@Profile("map")
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
    public List<Vet> findBySpeciality(Speciality speciality) {

        return this.findAll()
                .stream()
                .filter(vet -> vet.getSpecialities() != null && vet.getSpecialities().contains(speciality))
                .collect(toList());
    }
}
