package com.EMS.demo.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public abstract class EmailService implements EmailSender {
    private JavaMailSender emailSender;
    public EmailService(JavaMailSender emailSender){
        this.emailSender=emailSender;
    }
    @Async
    public void sendEmail(String to, String email){
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMailMessage mimeMailMessage = new MimeMailMessage(mimeMessage);
        mimeMailMessage.setTo(to);
        mimeMailMessage.setSubject("Verification Email");
        mimeMailMessage.setText(email);
        mimeMailMessage.setFrom("baabaa@gmail.com");
        emailSender.send(mimeMessage);
        System.out.println("Email sent");
    }
}
