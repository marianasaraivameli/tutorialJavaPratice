package com.example.tutorialjavaexercises.controller;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.service.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

    @Autowired
    private ITutorialService tutorialService;


    // TODO: tratar exception de title null
    @PostMapping
    public ResponseEntity<Tutorial> insertTutorial(@RequestBody Tutorial tutorial) {
       Tutorial newTutorial = tutorialService.insertTutorial(tutorial);
       return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/published")
    public ResponseEntity<Tutorial> updateStatusPublished(@PathVariable Long id) {
        Optional<Tutorial> optionalTutorial = tutorialService.getById(id);
        if (optionalTutorial.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Tutorial tutorial = optionalTutorial.get();
        tutorial.setStatus(Status.PUBLISHED);
        tutorial = tutorialService.update(tutorial);
        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }
}
