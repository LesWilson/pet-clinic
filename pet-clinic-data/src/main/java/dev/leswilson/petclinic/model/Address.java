package dev.leswilson.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 123L;

    /* Sonar Ignore Start */
    private String line1;
    private String line2;
    private String city;
    private String county;
    private String country;
    private String postcode;
    /* Sonar Ignore End */
}
