package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "VaccineSeq", initialValue = 1, allocationSize = 1)
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VaccineSeq")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    public Vaccine(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


