package com.wasathDev.LearingSpring.exception;

import com.wasathDev.LearingSpring.constant.ErrorCodes;
import com.wasathDev.LearingSpring.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDataNotFoundException(DataNotFoundException exception) {

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(ErrorCodes.LS001)
                .message(exception.getMessage())
                .variable(exception.getVariable())
                .build();
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(DuplicateDataFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateDataFoundException(DuplicateDataFoundException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(ErrorCodes.LS002)
                .message(exception.getMessage())
                .variable(exception.getVariable())
                .build();
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(GeneralException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(ErrorCodes.LS003)
                .message(exception.getMessage())
                .variable(exception.getVariable())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }
}
