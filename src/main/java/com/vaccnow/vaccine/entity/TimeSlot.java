package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "TimeSlotSeq", initialValue = 1, allocationSize = 1)
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TimeSlotSeq")
    private Long id;

    @Column
    private Integer year;

    @Column
    private Integer month;

    @Column
    private Integer day;

    @Column
    private Integer hour;

    @Column
    private Integer minute;

    public TimeSlot(Integer year, Integer month, Integer day, Integer hour, Integer minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
}
