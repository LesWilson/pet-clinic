package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.model.Pet;
import dev.leswilson.petclinic.services.OwnerService;
import dev.leswilson.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
@Profile("map")
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;

    public OwnerMapService(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner t) {
        super.delete(t);
    }

    @Override
    public Owner save(Owner owner) {
        // add logic to iterate through pets and save any that don't exist
        if(owner != null) {
            Set<Pet> pets = owner.getPets();
            if(!CollectionUtils.isEmpty(pets)) {
                pets.stream().filter(Pet::isNew).forEach(pet -> {
                    pet.setOwner(owner);
                    petService.save(pet);
                });
            }
            return super.save(owner);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
//        List<Owner> owners = map.entrySet()
//                .stream()
//                .filter(entry -> lastName.equals(entry.getValue().getLastName()))
//                .map(e -> e.getValue())
//                .collect(toList());

        return this.findAll()
                .stream()
                .filter(entry -> lastName.equalsIgnoreCase(entry.getLastName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {

        //todo - impl
        return null;
    }
}
