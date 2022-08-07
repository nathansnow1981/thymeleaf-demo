package com.example.thymeleaf;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_message")
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "message", length = 1024)
    private String message;
}
