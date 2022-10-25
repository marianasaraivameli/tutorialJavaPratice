package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.model.Tutorial;

import java.util.List;
import java.util.Optional;

public interface ITutorialService {
    Tutorial insertTutorial(Tutorial tutorial);
    List<Tutorial> getAll();
    Optional<Tutorial> getById(Long id);
    Tutorial update(Tutorial tutorial);
    void deleteAll();
    void deleteById(Long id);
    List<Tutorial> getPublished();
    List<Tutorial> getByTitle(String title);
}
