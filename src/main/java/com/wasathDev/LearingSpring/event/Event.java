package com.wasathDev.LearingSpring.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class Event extends ApplicationEvent {
    private String message;

    public Event(Object source, String message) {
        super(source);
        this.message = message;
    }

}