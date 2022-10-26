package com.example.tutorialjavaexercises.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Column(length = 50, nullable = false)
    private String title;

    @Size(min = 10, message = "A descrrição precisa de no mínimo 10 caracteres")
    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;
}
