package dev.leswilson.petclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    private IndexController controller = new IndexController();

    private MockMvc mvc;

    private static String[] urisToTest = {"","/", "/index.html"};

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("Check all forms of main index page uri")
    @RepeatedTest(value = 3, name = "checking uri: {currentRepetition}/{totalRepetitions}")
    void checkIndexPageUris(RepetitionInfo info) throws Exception {
        String uriToTest = urisToTest[info.getCurrentRepetition()-1];
        mvc.perform(get(uriToTest))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    void find() throws Exception {
        mvc.perform(get("/oops"))
                .andExpect(view().name("error"));
    }

}