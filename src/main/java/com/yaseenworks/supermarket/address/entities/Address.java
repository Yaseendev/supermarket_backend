package com.yaseenworks.supermarket.address.entities;

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
public class Address {
    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Integer id;
    private String city;
    private String state;
    private String street;
    private String blockNumber;
    private String floorNumber;
    private String phoneNumber;
    private String buildingName;
    private String apartmentNumber;
    private String additionalInfo;
    private String label;
    @OneToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "position_id",
            referencedColumnName = "id"
    )
    private GeoLocation position;
}
