package com.example.tutorialjavaexercises.controller;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.service.TutorialService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private List<Tutorial> tutorialListSetup = new ArrayList<>();
    private Tutorial tutorialSetup;

    @BeforeEach
    public void setup() {
        tutorialSetup = new Tutorial(1L, "Title", "Description", Status.DRAFT);
        tutorialListSetup.add(tutorialSetup);
    }

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
    void updateStatusPublished_returnUpdateTutorial_whenTutorialIsValid() throws Exception {

        BDDMockito.when(service.getById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(tutorialSetup));


        BDDMockito.when(service.update(ArgumentMatchers.any(Tutorial.class)))
                .thenReturn(tutorialSetup);

        tutorialSetup.setStatus(Status.PUBLISHED);

        ResultActions result = mockMvc.perform(
                patch("/tutorial/{id}/published", tutorialSetup.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorialSetup))
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Title")));

    }

    @Test
    void getByTitle_returnTutorialByTitle_whenSuccess() throws Exception {
        BDDMockito.when(service.getByTitle(ArgumentMatchers.anyString()))
                .thenReturn(Collections.singletonList(tutorialListSetup.get(0)));

        ResultActions result = mockMvc.perform(
                get("/tutorial/title/{title}", tutorialSetup.getTitle())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", CoreMatchers.is(tutorialListSetup.get(0).getTitle())));
    }

    @Test
    void getPublished_returnTutorialWithStatusPublished_whenSuccess() throws Exception {
        BDDMockito.when(service.getPublished())
                .thenReturn(Collections.singletonList(tutorialListSetup.get(0)));

        ResultActions result = mockMvc.perform(
                get("/tutorial/published")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", CoreMatchers.is(tutorialListSetup.get(0).getTitle())));
    }
}