package com.example.thymeleaf.entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_cv")
public class StudentCV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "file_path", length = 1024)
    private String filepath;
}