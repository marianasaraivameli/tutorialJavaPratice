package com.example.tutorialjavaexercises.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionDetails {
    private String title;
    private String message;
    private String timestamp;
}
