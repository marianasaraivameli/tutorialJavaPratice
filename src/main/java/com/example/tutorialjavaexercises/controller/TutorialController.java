package com.example.tutorialjavaexercises.controller;

import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.service.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

    @Autowired
    private ITutorialService tutorialService;

    @PostMapping
    public ResponseEntity<Tutorial> insertTutorial(@RequestBody Tutorial tutorial) {
       Tutorial newTutorial = tutorialService.insertTutorial(tutorial);
       return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
    }
}
