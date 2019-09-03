package dev.leswilson.petclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class to ensure lombok works and get coverage :)
 */
class AddressTest {

    @Test
    void testAddress() {
        Address address = new Address();
        address.setPostcode("PO");
        address.setCountry("UK");
        address.setCounty("Essex");
        address.setCity("Woodford");
        address.setLine1("Line1");
        address.setLine2("Line2");
        assertThat(address.getPostcode(), is("PO"));
        assertThat(address.getCountry(), is("UK"));
        assertThat(address.getCounty(), is("Essex"));
        assertThat(address.getCity(), is("Woodford"));
        assertThat(address.getLine1(), is("Line1"));
        assertThat(address.getLine2(), is("Line2"));
    }

}