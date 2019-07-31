package dev.leswilson.petclinic.services;

import dev.leswilson.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
