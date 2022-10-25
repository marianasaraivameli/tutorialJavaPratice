package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.exception.InvalidParamException;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return repository.save(tutorial);
    }

    @Override
    public List<Tutorial> getAll() {
        return null;
    }

    @Override
    public Tutorial getById(Long id) {
        return null;
    }

    @Override
    public Tutorial update(Tutorial tutorial) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Tutorial> getPublished() {
        return null;
    }

    @Override
    public List<Tutorial> getByTitle(String title) {
        return null;
    }
}