package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.services.OwnerService;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

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
        return super.save(owner.getId(), owner);
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
        List<Owner> owners = map.entrySet()
                .stream()
                .filter(entry -> lastName.equals(entry.getValue().getLastName()))
                .map(e -> e.getValue())
                .collect(toList());

        return owners.size() > 0 ? owners.get(0) : null;
    }
}
