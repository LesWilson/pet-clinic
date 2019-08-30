package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Profile("map")
@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet t) {
        super.delete(t);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Pet> findByOwner(Owner owner) {

        return this.findAll()
                .stream()
                .filter(entry -> entry.getOwner() != null && owner.getId().equals(entry.getOwner().getId()))
                .collect(toList());
    }
}
