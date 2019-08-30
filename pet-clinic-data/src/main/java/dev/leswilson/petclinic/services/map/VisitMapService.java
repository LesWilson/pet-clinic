package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.model.Visit;
import dev.leswilson.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Profile("map")
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public List<Visit> findByPet(Pet pet) {

        return this.findAll()
                .stream()
                .filter(entry -> entry.getPet() != null && pet.getId().equals(entry.getPet().getId()))
                .collect(toList());
    }

    @Override
    public List<Visit> findByOwner(Owner owner) {

        return this.findAll()
                .stream()
                .filter(visit ->
                    visit.getPet() != null &&
                            visit.getPet().getOwner() != null &&
                            owner.getId().equals(visit.getPet().getOwner().getId())
                )
                .collect(toList());
    }
}
