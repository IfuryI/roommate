package com.bh.roommate.application.impl.service.mail;

import com.bh.roommate.application.api.service.mail.MailSendingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MailSendingServiceImpl implements MailSendingService {

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);

        mailSender.send(msg);

        log.info(String.format("Успешно отправлено сообщение на почту: %s \n Содержание письма: %s", to, message));
    }
}
