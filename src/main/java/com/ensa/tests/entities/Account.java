package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ACCOUNTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double balance;
    private String rib;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<Operation> operations=new ArrayList<>();

    @ManyToOne
    private Client client;
}
