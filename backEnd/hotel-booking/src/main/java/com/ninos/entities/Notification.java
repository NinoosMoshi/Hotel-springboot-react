package com.ninos.entities;

import com.ninos.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @NotBlank(message = "recipient is required")
    private String recipient;

    @Column(columnDefinition = "LONGTEXT")
    private String body;

    private String bookingReference;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private final LocalDateTime createdAt = LocalDateTime.now();

}
