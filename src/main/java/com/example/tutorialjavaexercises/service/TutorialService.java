package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.exception.InvalidParamException;
import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService implements ITutorialService{

    private final TutorialRepository repository;

    @Override
    public Tutorial insertTutorial(Tutorial tutorial) {
        if(tutorial == null) {
            throw new InvalidParamException("Parâmetro não pode ser nulo!");
        }
        if (tutorial.getId() != null) {
            throw new InvalidParamException("O Tutorial não pode ter ID!");
        }
        // setando um status para o tutorial iniciar em Draft
        tutorial.setStatus(Status.DRAFT);
        return repository.save(tutorial);
    }

    @Override
    public List<Tutorial> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Tutorial> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Tutorial update(Tutorial tutorial) {
        if(tutorial == null) {
            throw new InvalidParamException("Parâmetro não pode ser nulo!");
        }
        if (tutorial.getId() == null) {
            throw new InvalidParamException("O Tutorial deve ter um ID!");
        }
        return repository.save(tutorial);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            throw new InvalidParamException("Parâmetro não pode ser nulo!");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Tutorial> getPublished() {
        return repository.findByStatus(Status.PUBLISHED);
    }

    @Override
    public List<Tutorial> getByTitle(String title) {
        return repository.findByTitleContaining(title);
    }
}
