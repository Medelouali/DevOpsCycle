package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OPERATIONS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Account account;
}
