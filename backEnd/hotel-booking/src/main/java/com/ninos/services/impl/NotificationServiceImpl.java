package com.ninos.services.impl;

import com.ninos.dtos.NotificationDTO;
import com.ninos.entities.Notification;
import com.ninos.enums.NotificationType;
import com.ninos.repositories.NotificationRepository;
import com.ninos.services.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.jsoup.Jsoup;



@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

//    @Async
//    @Override
//    public void sendEmail(NotificationDTO notificationDTO) {
//        log.info("Sending email");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(notificationDTO.getRecipient());
//        message.setSubject(notificationDTO.getSubject());
//        message.setText(notificationDTO.getBody());
//
//        javaMailSender.send(message);
//
//        // save to database
//        Notification notificationToSave = Notification.builder()
//                .recipient(notificationDTO.getRecipient())
//                .subject(notificationDTO.getSubject())
//                .body(notificationDTO.getBody())
//                .bookingReference(notificationDTO.getBookingReference())
//                .type(NotificationType.EMAIL)
//                .build();
//
//        notificationRepository.save(notificationToSave);
//
//    }



    @Async
    @Override
    public void sendEmail(NotificationDTO notificationDTO) {
        log.info("Sending email with HTML content");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(notificationDTO.getRecipient());
            helper.setSubject(notificationDTO.getSubject());
            helper.setText(notificationDTO.getBody(), true); // Enable HTML content

            javaMailSender.send(message);

            // Convert HTML email body to plain text before saving to the database
            String plainTextBody = Jsoup.parse(notificationDTO.getBody()).text();

            // Save to database
            Notification notificationToSave = Notification.builder()
                    .recipient(notificationDTO.getRecipient())
                    .subject(notificationDTO.getSubject())
//                    .body(notificationDTO.getBody())
                    .body(plainTextBody)
                    .bookingReference(notificationDTO.getBookingReference())
                    .type(NotificationType.EMAIL)
                    .build();

            log.info("Email body length: {}", notificationDTO.getBody().length());

            notificationRepository.save(notificationToSave);

        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }



    @Override
    public void sendSms() {

    }

    @Override
    public void sendWhatsApp() {

    }
}
