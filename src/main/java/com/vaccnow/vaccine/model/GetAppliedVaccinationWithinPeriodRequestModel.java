package com.vaccnow.vaccine.model;

import com.vaccnow.vaccine.entity.enums.VaccinationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAppliedVaccinationWithinPeriodRequestModel {
    private LocalDate start;
    private LocalDate end;
    private VaccinationStatus status;
}
