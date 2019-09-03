package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.exceptions.DataException;
import dev.leswilson.petclinic.model.Owner;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractMapServiceTest {

    @Test
    void saveThrowsExceptionWhenNullObjectPassedIn() {
        TestMap testMap = new TestMap();
        DataException thrownException = assertThrows(DataException.class, () -> testMap.save(null));
        assertThat(thrownException.getMessage(), is("Object passed in cannot be null"));
    }

    // Test class to ensure we can pass null to save method
    // Real classes all check for null
    private class TestMap extends AbstractMapService<Owner, Long> {

    }
}