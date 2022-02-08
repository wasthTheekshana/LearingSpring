package com.wasathDev.LearingSpring.controller;


import com.wasathDev.LearingSpring.publisher.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventPublisher eventPublisher;

    @Autowired
    public EventController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    ResponseEntity<String> publishEvent(){

        eventPublisher.publishAlertEvent("This is for testing");

        return ResponseEntity.status(HttpStatus.OK).body("Published");

    }

}
