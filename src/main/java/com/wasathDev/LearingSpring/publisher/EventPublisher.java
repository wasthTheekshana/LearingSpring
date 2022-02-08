package com.wasathDev.LearingSpring.publisher;


import com.wasathDev.LearingSpring.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async("ThreadPoolExecutor")
    public void publishAlertEvent(String message) {
        log.info("Event Published");
        Event alertEvent = new Event(this, message);
        applicationEventPublisher.publishEvent(alertEvent);
    }
}