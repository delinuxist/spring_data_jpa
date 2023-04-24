package com.example.spring.data.jda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",
    uniqueConstraints = @UniqueConstraint(
            name = "email_unique",
            columnNames = "email_address"
    )
)
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID studentId;
    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String email;
    @Embedded
    private Guardian guardian;
}
