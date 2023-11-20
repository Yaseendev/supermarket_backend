package com.yaseenworks.supermarket.product.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nutrition {

    @Id
    @SequenceGenerator(
            name = "nutrition_sequence",
            sequenceName = "nutrition_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nutrition_sequence"
    )
    private Integer id;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Nutrition nutrition = (Nutrition) o;
        return getId() != null && Objects.equals(getId(), nutrition.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
