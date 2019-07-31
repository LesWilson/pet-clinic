package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.VetService;

import java.util.Set;

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
        return super.save(vet.getId(), vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

}
