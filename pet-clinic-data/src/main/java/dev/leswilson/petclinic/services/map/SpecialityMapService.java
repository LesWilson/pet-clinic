package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.model.Speciality;
import dev.leswilson.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {

}
