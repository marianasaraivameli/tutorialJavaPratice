package com.example.tutorialjavaexercises.repository;

import com.example.tutorialjavaexercises.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // não precisa colocar quando usamos o Jpa Repesitory
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByTitleContaining(String title);
}
