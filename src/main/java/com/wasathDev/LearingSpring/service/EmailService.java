package com.wasathDev.LearingSpring.service;

import java.util.Properties;

public interface EmailService {

    String sendEmail(Properties properties);
    String sendMultipleEmail(Properties properties);
}
