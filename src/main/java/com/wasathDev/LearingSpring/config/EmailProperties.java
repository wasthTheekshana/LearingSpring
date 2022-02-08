package com.wasathDev.LearingSpring.config;

import java.util.Date;
import java.util.Properties;

public class EmailProperties {
    public static Properties getEmailProperties(){

        Properties properties = new Properties();

        properties.setProperty("to", "myemail");
        properties.setProperty("from", "automated@noreply");
        properties.setProperty("subject", "Automated Email");
        properties.setProperty("template_name", "email_body");

        properties.setProperty("API", "TEST API");
        properties.setProperty("reportedOn", new Date() + "");
        properties.setProperty("message", "Incident occurred in the Test API");

        return properties;
    }

}