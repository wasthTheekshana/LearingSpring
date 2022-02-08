package com.wasathDev.LearingSpring.listner;

import com.wasathDev.LearingSpring.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventListener implements ApplicationListener<Event> {

    @Async
    @Override
    public void onApplicationEvent(Event event) {
        log.info("Event Listened " + event.getMessage());
    }
}