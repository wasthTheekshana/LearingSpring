package com.wasathDev.LearingSpring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private final String message;
    private final String variable;


}
