package com.vaccnow.vaccine.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAvailableTimePerBranchRequestModel {
    private Long branchId;
    private LocalDate day;
}
