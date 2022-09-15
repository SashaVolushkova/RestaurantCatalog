package org.example.restaurant.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_type")
public class FoodTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOOD_TYPE_SEQ")
    @SequenceGenerator(name = "FOOD_TYPE_SEQ", sequenceName = "FOOD_TYPE_SEQ", allocationSize = 1)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 200)
    private String description;
}
