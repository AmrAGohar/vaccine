package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "CitizenSeq", initialValue = 10, allocationSize = 1)
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CitizenSeq")
    private Long id;

    @Column
    private String nationalId;

    @Column
    private String name;

    @Column
    private String email;
}
