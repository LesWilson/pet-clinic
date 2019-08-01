package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class BaseEntity implements Serializable {

    private Long id;

    public boolean isNew() {
        return id == null;
    }
}
