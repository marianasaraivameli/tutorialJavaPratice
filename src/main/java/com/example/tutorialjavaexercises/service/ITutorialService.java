package com.example.tutorialjavaexercises.service;

import com.example.tutorialjavaexercises.model.Tutorial;

import java.util.List;

public interface ITutorialService {
    Tutorial insertTutorial(Tutorial tutorial);
    List<Tutorial> getAll();
    Tutorial getById(Long id);
    Tutorial update(Tutorial tutorial);
    void deleteAll();
    void deleteById(Long id);
    List<Tutorial> getPublished();
    List<Tutorial> getByTitle(String title);
}
