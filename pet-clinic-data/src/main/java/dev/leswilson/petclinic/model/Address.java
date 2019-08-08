package dev.leswilson.petclinic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String line1;
    private String line2;
    private String city;
    private String county;
    private String country;
    private String postcode;
}
