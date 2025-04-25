package com.EMS.demo.Service;

import com.EMS.demo.Entities.ConfirmationToken;
import jakarta.mail.MessagingException;

public interface EmailSender {
    void sendEmail(String to, String email, ConfirmationToken code) throws MessagingException;
}
