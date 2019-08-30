package dev.leswilson.petclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"pets"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@TableGenerator(
        name="idGen",
        table="next_ids",
        pkColumnName = "table_name",
        valueColumnName = "next_id",
        pkColumnValue="owner",
        allocationSize=5
)
public class Owner extends Person {

//    @Builder
//    public Owner(Long id, String createdBy, Date createdAt, String updatedBy, Date updatedAt, String firstName, String lastName, Address homeAddress, String telephone, Set<Pet> pets) {
//        super(id, createdBy, createdAt, updatedBy, updatedAt, firstName, lastName);
//        this.homeAddress = homeAddress;
//        this.telephone = telephone;
//        this.pets = pets;
//    }

    /* Sonar Ignore Start */
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "line1", column = @Column(name = "home_address_line_1")),
            @AttributeOverride(name = "line2", column = @Column(name = "home_address_line_2")),
            @AttributeOverride(name = "county", column = @Column(name = "home_address_county")),
            @AttributeOverride(name = "country", column = @Column(name = "home_address_country")),
            @AttributeOverride(name = "postcode", column = @Column(name = "home_address_postcode")),
            @AttributeOverride(name = "city", column = @Column(name = "home_address_city"))
    })
    private Address homeAddress;

    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    /* Sonar Ignore End */
}
