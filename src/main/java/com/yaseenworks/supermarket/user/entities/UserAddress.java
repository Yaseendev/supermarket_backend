package com.yaseenworks.supermarket.user.entities;

import com.yaseenworks.supermarket.address.entities.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddress {
    @Id
    @SequenceGenerator(
            name = "user_address_sequence",
            sequenceName = "user_address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_address_sequence"
    )
    private Integer id;

    @OneToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id"
    )
    private Address address;

    private Boolean isDefault = false;
}
