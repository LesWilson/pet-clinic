package dev.leswilson.petclinic.controllers;

import dev.leswilson.petclinic.model.Vet;
import dev.leswilson.petclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
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
class VetControllerTest {

    @Mock
    private VetService service;
    @InjectMocks
    private VetController controller;

    private Set<Vet> vets;

    private MockMvc mvc;

    private static String[] urisToTest = {"/vets", "/vets/", "/vets/index", "/vets/index.html"};

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Jack");
        vet.setLastName("London");
        vets.add(vet);

        Vet vet1 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Jackson");
        vet1.setLastName("Croydon");
        vets.add(vet1);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Check all forms of vet index page uri")
    @RepeatedTest(value = 4, name = "checking uri: {currentRepetition}/{totalRepetitions}")
    void checkIndexPageUris(RepetitionInfo info) throws Exception {
        when(service.findAll()).thenReturn(vets);
        String uriToTest = urisToTest[info.getCurrentRepetition()-1];
        mvc.perform(get(uriToTest))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));

        verify(service, times(1)).findAll();
    }
}