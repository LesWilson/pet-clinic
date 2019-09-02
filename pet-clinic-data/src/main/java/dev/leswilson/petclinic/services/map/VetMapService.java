package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.VetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Profile("map")
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public List<Vet> findBySpeciality(Speciality speciality) {

        return this.findAll()
                .stream()
                .filter(vet -> vet.getSpecialities() != null && vet.getSpecialities().contains(speciality))
                .collect(toList());
    }
    @Override
    public Vet findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(entry -> lastName.equalsIgnoreCase(entry.getLastName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vet> findAllByLastNameLike(String lastName) {

        return this.findAll()
                .stream()
                .filter(entry -> StringUtils.contains(entry.getLastName(), lastName))
                .collect(Collectors.toList());
    }

}
