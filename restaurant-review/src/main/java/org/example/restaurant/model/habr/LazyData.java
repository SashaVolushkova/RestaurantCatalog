package org.example.restaurant.model.habr;

import lombok.Getter;
import lombok.Setter;
import org.example.restaurant.model.RestaurantEntity;

import javax.persistence.*;

@Entity
public class LazyData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @Column
    @Getter
    private String data;

    @ManyToOne
    @JoinColumn(name="data_id", nullable=false)
    private Data dataLink;
}
