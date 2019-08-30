package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.PetType;
import dev.leswilson.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("map")
@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

}
