package com.wasathDev.LearingSpring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DuplicateDataFoundException extends RuntimeException{

    private String message;
    private String variable;
}
