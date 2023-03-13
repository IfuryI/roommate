package com.bh.roommate.application.api.service.mail;

public interface MailSendingService {

    public void send(String to, String subject, String message);
}
