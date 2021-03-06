package dev.leswilson.petclinic.controllers;

import dev.leswilson.petclinic.model.Owner;
import dev.leswilson.petclinic.services.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService service;
    @InjectMocks
    private OwnerController controller;

    private Set<Owner> owners;

    private MockMvc mvc;

    private static String[] urisToTest = {"/owners", "/owners/", "/owners/index", "/owners/index.html"};

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Jack");
        owner.setLastName("London");
        owners.add(owner);

        Owner owner1 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Jackson");
        owner1.setLastName("Croydon");
        owners.add(owner1);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Check all forms of owner index page uri")
    @RepeatedTest(value = 4, name = "checking uri: {currentRepetition}/{totalRepetitions}")
    void checkIndexPageUris(RepetitionInfo info) throws Exception {
        when(service.findAll()).thenReturn(owners);
        String uriToTest = urisToTest[info.getCurrentRepetition()-1];
        mvc.perform(get(uriToTest))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

        verify(service, times(1)).findAll();
    }

    @Test
    void find() throws Exception {
        mvc.perform(get("/owners/find"))
                .andExpect(view().name("notimplemented"));
        verifyZeroInteractions(service);
    }
}