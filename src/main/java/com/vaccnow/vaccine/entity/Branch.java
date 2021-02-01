package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "BranchSeq", initialValue = 1, allocationSize = 1)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BranchSeq")
    private Long id;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "branch")
    private Set<BranchVaccine> vaccines;

//    @OneToMany(mappedBy = "branch")
//    private Set<Vaccination> vaccination;

    public Branch(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
