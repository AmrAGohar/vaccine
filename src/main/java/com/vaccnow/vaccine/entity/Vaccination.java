package com.vaccnow.vaccine.entity;

import com.vaccnow.vaccine.entity.enums.PaymentMethod;
import com.vaccnow.vaccine.entity.enums.VaccinationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "VaccinationSeq", initialValue = 10, allocationSize = 1)
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VaccinationSeq")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSlot;

    @Column
    @Enumerated(EnumType.STRING)
    private VaccinationStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch", referencedColumnName = "id")
    private Branch branch;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vaccine", referencedColumnName = "id")
    private Vaccine vaccine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen", referencedColumnName = "id")
    private Citizen citizen;

    @Override
    public String toString() {
        return "Vaccination{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", timeSlot=" + timeSlot +
                ", status=" + status +
                '}';
    }
}
