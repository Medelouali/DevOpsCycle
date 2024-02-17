package com.ensa.tests.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Double price;
    private Double rating;
    private String description;
    @ManyToOne
    private Category category;
}
