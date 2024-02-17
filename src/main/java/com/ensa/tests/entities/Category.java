package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy="category")
    private Collection<Product> products=new ArrayList<>();
}
