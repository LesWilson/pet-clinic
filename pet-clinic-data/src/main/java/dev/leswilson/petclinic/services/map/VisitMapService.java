package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import dev.leswilson.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit t) {
        super.delete(t);
    }

    @Override
    public Visit save(Visit visit) {
        return super.save(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findByPet(Pet pet) {

        return this.findAll()
                .stream()
                .filter(entry -> entry.getPet() != null && pet.getId().equals(entry.getPet().getId()))
                .collect(toSet());
    }

    @Override
    public Set<Visit> findByOwner(Owner owner) {

        return this.findAll()
                .stream()
                .filter(visit ->
                    visit.getPet() != null &&
                            visit.getPet().getOwner() != null &&
                            owner.getId().equals(visit.getPet().getOwner().getId())
                )
                .collect(toSet());
    }
}
