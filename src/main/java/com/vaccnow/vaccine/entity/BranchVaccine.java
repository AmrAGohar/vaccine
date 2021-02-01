package com.vaccnow.vaccine.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BranchVaccine {

    @EmbeddedId
    BranchVaccineKey id;

    @ManyToOne
    @MapsId("branchId")
    @JoinColumn(name = "branchId")
    Branch branch;

    @ManyToOne
    @MapsId("vaccineId")
    @JoinColumn(name = "vaccineId")
    Vaccine vaccine;

    @Column
    int availableCount;
}
