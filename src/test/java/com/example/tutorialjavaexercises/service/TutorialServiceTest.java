package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.repository.TutorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TutorialServiceTest {

    @InjectMocks
    private TutorialService service;

    @Mock
    private TutorialRepository repository;

    @BeforeEach
    public void setup() {
        repository.save(new Tutorial(1L, "Title 1", "Example description", Status.DRAFT));
    }

    @Test
    void insertTutorial_returnNewTutorial_whenTutorialValid() {
        BDDMockito.when(repository.save(ArgumentMatchers.any(Tutorial.class)))
                .thenReturn(new Tutorial(2L, "Title 2", "Example description", Status.DRAFT));


        Tutorial newTutorial = service.insertTutorial(new Tutorial(null, "Title 2", "Example description", null));

        assertThat(newTutorial).isNotNull();
        assertThat(newTutorial.getId()).isPositive();
        assertThat(newTutorial.getStatus()).isEqualTo(Status.DRAFT);
    }

    @Test
    public void getAll_returnListTutorial_whenListExist() {
        List<Tutorial> mockListTutorial = new ArrayList<>();
        mockListTutorial.add(new Tutorial(1L, "Title 1", "Example description", Status.DRAFT));

        BDDMockito.when(repository.findAll())
                .thenReturn(mockListTutorial);

        List<Tutorial> tutorialList = service.getAll();

        assertThat(tutorialList).isNotNull();
        assertThat(tutorialList.get(0).getTitle()).isEqualTo(mockListTutorial.get(0).getTitle());
        assertThat(tutorialList.get(0).getStatus()).isEqualTo(Status.DRAFT);
    }

    @Test
    void getById_returnTutorialById_whenTutorialExist() {
        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(new Tutorial(3L, "Title 3", "Example description", Status.DRAFT)));

        Optional<Tutorial> tutorial = service.getById(3L);

        assertThat(tutorial).isPresent();
        assertThat(tutorial.get().getId()).isEqualTo(3L);
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