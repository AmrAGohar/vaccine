package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "AddressSeq", initialValue = 1, allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSeq")
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String street;

    @Column
    private String description;


    public Address(String country, String city, String district, String street, String description) {
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.description = description;
    }
}
