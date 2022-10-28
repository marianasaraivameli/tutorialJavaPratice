package com.example.tutorialjavaexercises.repository;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TutorialRepositoryTest {

    @Autowired
    private TutorialRepository repository;


    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void save_returnTutorial_whenTutorialIsValid() {
        Tutorial newTutorial = new Tutorial(null, "Title 1", "Example description", Status.DRAFT);

        Tutorial tutorialSaved = repository.save(newTutorial);

        assertThat(tutorialSaved).isNotNull();
        assertThat(tutorialSaved.getId()).isPositive();
        assertThat(tutorialSaved.getStatus()).isEqualTo(Status.DRAFT);
        assertThat(tutorialSaved.getTitle()).isEqualTo(newTutorial.getTitle());
    }

    @Test
    public void findById_returnTutorial_whenIdTutorialExist() {
        Tutorial newTutorial = new Tutorial(null, "Title 1", "Example description", Status.DRAFT);
        Tutorial tutorialSaved = repository.save(newTutorial);

        Optional<Tutorial> tutorialOptional = repository.findById(tutorialSaved.getId());

        assertThat(tutorialOptional.isPresent()).isTrue();
        assertThat(tutorialOptional.get().getId()).isPositive();
        assertThat(tutorialOptional.get().getStatus()).isEqualTo(Status.DRAFT);
        assertThat(tutorialOptional.get().getTitle()).isEqualTo(newTutorial.getTitle());
    }

    @Test
    public void findById_returnTutorial_whenIdTutorialNotExist() {
        // neste caso não tem preparo de cenário, apenas pesquisamos algo no banco vazio com o código abaixo
        Optional<Tutorial> tutorialOptional = repository.findById(1L);

        assertThat(tutorialOptional.isEmpty()).isTrue();
    }

    @Test
    public void findByStatus_returnListTutorial_whenStatusExist() {
        Tutorial newTutorial = new Tutorial(null, "Title 1", "Example description", Status.DRAFT);
        Tutorial tutorialSaved = repository.save(newTutorial);

        List<Tutorial> tutorialList = repository.findByStatus(Status.DRAFT);
        assertThat(tutorialList.size()).isEqualTo(1);
        assertThat(tutorialList.get(0).getId()).isEqualTo(tutorialSaved.getId());
    }

    @Test
    public void findByStatus_returnListTutorial_whenStatusNotExist() {
        List<Tutorial> tutorialList = repository.findByStatus(Status.DRAFT);

        assertThat(tutorialList.size()).isEqualTo(0);
    }

    @Test
    public void findByStatus_returnListTutorial_whenStatusDiferent() {
        List<Tutorial> tutorialList = repository.findByStatus(Status.PUBLISHED);

        assertThat(tutorialList.size()).isEqualTo(0);
    }


    @Test
    public void deleteAll_returnListEmpty_whenDeleteAll(){
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    public void deleteById_returnListNotId_whenDeleteByItem(){
        Tutorial newTutorial = new Tutorial(1L, "Title 1", "Example description", Status.DRAFT);
        Tutorial tutorialSaved = repository.save(newTutorial);

        repository.deleteById(tutorialSaved.getId());

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    public void findAll_returnListTutorial_whenListTutorialExist() {
        Tutorial newTutorial = new Tutorial(null, "Title 1", "Example description", Status.DRAFT);
        repository.save(newTutorial);

        List<Tutorial> tutorialList = repository.findAll();

        assertThat(tutorialList.size()).isEqualTo(1);
        assertThat(newTutorial.getId()).isEqualTo(tutorialList.get(0).getId());
    }

    @Test
    public void findAll_returnListTutorial_whenListTutorialNotExist() {
        List<Tutorial> tutorialList = repository.findAll();

        assertThat(tutorialList.size()).isEqualTo(0);
    }
}
