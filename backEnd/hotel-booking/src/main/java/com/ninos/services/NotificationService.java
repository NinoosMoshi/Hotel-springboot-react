package com.ninos.services;

import com.ninos.dtos.NotificationDTO;

public interface NotificationService {

    void sendEmail(NotificationDTO notificationDTO);
    void sendSms();
    void sendWhatsApp();

}
