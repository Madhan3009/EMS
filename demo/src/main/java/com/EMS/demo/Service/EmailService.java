package com.EMS.demo.Service;

import com.EMS.demo.Entities.ConfirmationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {
    private JavaMailSender emailSender;
    public EmailService(JavaMailSender emailSender){
        this.emailSender=emailSender;
    }
    @Async
    public void sendEmail(String to, String email,ConfirmationToken token) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMailMessage mimeMailMessage = new MimeMailMessage(mimeMessage);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMailMessage.setTo(to);
        mimeMailMessage.setSubject("Verification Email");
        mimeMailMessage.setText(email);
        mimeMailMessage.setFrom("baabaa@gmail.com");
        emailSender.send(mimeMessage);
        String content = "Dear [[name]],<br>\"\n" +
                "            + \"Please click the link below to verify your registration:<br>\"\n" +
                "            + \"<h3><a href=\\\"[[CODE]]\\\" target=\\\"_self\\\">Verification Code</a></h3>\"\n" +
                "            + \"Thank you,<br>\"\n" +
                "            + \"Your company name.";
        content = content.replace("[[name]]", email);
        content = content.replace("[[CODE]]", token.getConfirmationToken());
        mimeMessageHelper.setText(content, true);
        System.out.println("Email sent");
    }

    }
