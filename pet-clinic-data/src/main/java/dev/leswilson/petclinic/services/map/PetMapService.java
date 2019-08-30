package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Profile("map")
@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public List<Pet> findByOwner(Owner owner) {

        return this.findAll()
                .stream()
                .filter(entry -> entry.getOwner() != null && owner.getId().equals(entry.getOwner().getId()))
                .collect(toList());
    }
}
