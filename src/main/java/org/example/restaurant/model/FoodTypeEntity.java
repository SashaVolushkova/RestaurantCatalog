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


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FoodTypeEntity)) return false;
        final FoodTypeEntity other = (FoodTypeEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        return Objects.equals(this$name, other$name);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FoodTypeEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }
}
