package com.example.tutorialjavaexercises.controller;

import com.example.tutorialjavaexercises.model.Status;
import com.example.tutorialjavaexercises.model.Tutorial;
import com.example.tutorialjavaexercises.service.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

    @Autowired
    private ITutorialService tutorialService;


    @PostMapping
    public ResponseEntity<Tutorial> insertTutorial(@Valid @RequestBody Tutorial tutorial) {
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

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Tutorial>> getByTitle(@PathVariable String title) {
        List<Tutorial> tutorialList = tutorialService.getByTitle(title);
        return new ResponseEntity<>(tutorialList, HttpStatus.OK);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> getPublished() {
        List<Tutorial> tutorialList = tutorialService.getPublished();
        return new ResponseEntity<>(tutorialList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAll() {
        return new ResponseEntity<>(tutorialService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll() {
        tutorialService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tutorialService.getById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
