package com.ninos;

import com.ninos.dtos.NotificationDTO;
import com.ninos.enums.NotificationType;
import com.ninos.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HotelBookingApplication {

//	@Autowired
//	private NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		NotificationDTO notificationDTO = NotificationDTO.builder()
//				.type(NotificationType.EMAIL)
//				.recipient("danielmoshi1980@gmail.com")
//				.body("I am testing this from command line")
//				.subject("Testing Email")
//				.build();
//		notificationService.sendEmail(notificationDTO);
//	}
}
