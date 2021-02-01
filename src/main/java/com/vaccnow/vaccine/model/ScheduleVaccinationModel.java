package com.vaccnow.vaccine.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleVaccinationModel {
    private String nationalId;
    private String name;
    private String email;
    private LocalDateTime timeSlot;
}
