package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TutorialServiceTest {

    @InjectMocks
    private TutorialService service;

    @Mock
    private TutorialRepository repository;

    @Test
    void insertTutorial_returnNewTutorial_whenTutorialValid() {
        BDDMockito.when(repository.save(ArgumentMatchers.any(Tutorial.class)))
                .thenReturn(new Tutorial(1L, "Title 1", "Example description", Status.DRAFT));


        Tutorial newTutorial = service.insertTutorial(new Tutorial(null, "Title 1", "Example description", null));

        assertThat(newTutorial).isNotNull();
        assertThat(newTutorial.getId()).isPositive();
        assertThat(newTutorial.getStatus()).isEqualTo(Status.DRAFT);
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getPublished() {
    }

    @Test
    void getByTitle() {
    }
}