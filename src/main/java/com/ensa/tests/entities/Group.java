package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GROUPS")
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
    private Collection<Client> clients=new ArrayList<>();
}
