package com.wasathDev.LearingSpring.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class SendEmailScheduler {

    @Scheduled(initialDelayString = "${scheduler.initial.delay}" ,
            fixedDelayString = "${scheduler.fixed.delay}")
    public void sendEmail(){
         log.info("STARED SENDING EMAIL");
    }

}
