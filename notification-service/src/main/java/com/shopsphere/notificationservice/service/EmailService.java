package com.shopsphere.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    // JavaMailSender is used to send emails
    private final JavaMailSender mailSender;

    // Send email to customer
    public void sendEmail(String to, String subject, String body) {

        // Create a simple email message
        SimpleMailMessage message = new SimpleMailMessage();

        // Set recipient email address
        message.setTo(to);

        // Set email subject
        message.setSubject(subject);

        // Set email body
        message.setText(body);

        // Send the email
        mailSender.send(message);

        System.out.println("Email sent to: " + to);
    }
}