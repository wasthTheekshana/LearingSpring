package com.wasathDev.LearingSpring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public String sendEmail(Properties properties) {
        try {
            return sendEmailWithTemplate(StringUtils.commaDelimitedListToStringArray(
                            (String) properties.get("to")),
                    (String) properties.get("from"),
                    (String) properties.get("subject"),
                    properties.getProperty("template_name"),
                    properties
            );

        } catch (Exception e) {
            throw e;
        }
    }

    public String sendEmailWithTemplate(String[] to, String from, String subject, String templateName,
                                        Properties properties) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        String bodyContent;
        String msg;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            bodyContent = processEmailTemplate(templateName, properties);
            helper.setText(bodyContent, true);

            log.debug(message.toString());

            emailSender.send(message);
            msg = "Sent Automated Incident Email";

            log.info("--------------------------------------------------------------------");
            log.info("MAIL SENT TO : {} || USING TEMPLATE : {} || FIRST ATTEMPT ON: {}", to, templateName, new Date().toString());
            log.info("--------------------------------------------------------------------");

        } catch (Exception e) {
            log.error("----------------------------------------------------------------------");
            log.error("FAILED TO SEND EMAIL TO : {}", Arrays.toString(to));
            log.error(e.getMessage());
            log.error("----------------------------------------------------------------------");
            msg = "Failed to Send Automated Incident Email";

        }
        return msg;
    }

    private String processEmailTemplate(String templateName, Properties properties) {
        Context context = new Context();
        properties.keySet().forEach(key -> context.setVariable(key.toString(), properties.get(key)));
        return templateEngine.process(templateName, context);
    }

    @Async("ThreadPoolExecutor")
    @Override
    public String sendMultipleEmail(Properties properties) {
        try {

            return sendEmailWithTemplate(StringUtils.commaDelimitedListToStringArray(
                            (String) properties.get("to")),
                    (String) properties.get("from"),
                    (String) properties.get("subject"),
                    properties.getProperty("template_name"),
                    properties
            );
        } catch (Exception e) {
            throw e;
        }
    }
}
