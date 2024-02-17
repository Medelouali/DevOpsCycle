package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "CLIENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String username;
    private Integer age;
    private Boolean isActive;
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Account> accounts=new ArrayList<>();

    @OneToOne(mappedBy = "client")
    private KYC kyc;

    @ManyToMany
    private Collection<Group> groups;
}
