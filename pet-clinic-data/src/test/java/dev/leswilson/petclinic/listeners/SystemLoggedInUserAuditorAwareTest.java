package dev.leswilson.petclinic.listeners;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class SystemLoggedInUserAuditorAwareTest {

    @Test
    void getCurrentAuditor() {
        SystemLoggedInUserAuditorAware aware = new SystemLoggedInUserAuditorAware();
        Optional<String> currentAuditor = aware.getCurrentAuditor();
        assertThat(currentAuditor, is(notNullValue()));
        assertThat(currentAuditor.isPresent(), is(true));
        //noinspection OptionalGetWithoutIsPresent
        assertThat(currentAuditor.get(), is(notNullValue()));
    }
}