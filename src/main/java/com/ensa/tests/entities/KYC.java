package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KYCS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KYC {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String socialNumber;
    @OneToOne
    private Client client;
}
