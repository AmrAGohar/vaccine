package com.vaccnow.vaccine.model;

import com.vaccnow.vaccine.entity.enums.PaymentMethod;
import com.vaccnow.vaccine.entity.enums.VaccinationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VaccinationModel {
    private Long id;
    private PaymentMethod paymentMethod;
    private LocalDateTime timeSlot;
    private VaccinationStatus status;
    private Long branchId;
    private Long vaccineId;
    private Long citizenId;
}
