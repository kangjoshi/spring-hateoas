package edu.til.springhateoas.controller;

import edu.til.springhateoas.component.PersonResourceAssembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonController.class)
@Import({PersonResourceAssembler.class})
class PersonControllerTest {

    private static final String BASE_PATH = "http://localhost/persons";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindOnePersonThenGetOnePerson() throws Exception {
        mockMvc.perform(get("/persons/1").accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("Jack")))
                .andExpect(jsonPath("age", is(37)))
                .andExpect(jsonPath("_links.self.href", is(BASE_PATH + "/1")))
                .andExpect(jsonPath("_links.list.href", is(BASE_PATH)));
    }



}