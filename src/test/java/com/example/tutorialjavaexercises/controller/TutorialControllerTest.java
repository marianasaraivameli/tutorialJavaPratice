package com.example.tutorialjavaexercises.controller;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.service.TutorialService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TutorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TutorialService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void insertTutorial_returnNewTutorial_whenTutorialIsValid() throws Exception {
        BDDMockito.when(service.insertTutorial(ArgumentMatchers.any(Tutorial.class)))
                .thenReturn(new Tutorial(1L, "Title", "Description", Status.DRAFT));

        Tutorial tutorial = new Tutorial(null, "Title", "Description", null);

        ResultActions result = mockMvc.perform(
                post("/tutorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorial))
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Title")));

        // exemplo Mauri sobre retorno de uma unidade, sem ser objeto
//        response.andExpect(status().isOk())
//                .andExpect(jsonPath("$", CoreMatchers.is(propertyValue.doubleValue())));
    }

    @Test
    void updateStatusPublished() {
    }

    @Test
    void getByTitle() {
    }

    @Test
    void getPublished() {
    }
}