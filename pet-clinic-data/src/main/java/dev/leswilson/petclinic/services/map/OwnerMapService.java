package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

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
        return super.save(owner);
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
}
