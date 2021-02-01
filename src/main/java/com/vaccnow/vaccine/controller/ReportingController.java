package com.vaccnow.vaccine.controller;

import com.vaccnow.vaccine.model.GetAppliedVaccinationWithinPeriodRequestModel;
import com.vaccnow.vaccine.model.VaccinationModel;
import com.vaccnow.vaccine.model.VaccinationModelList;
import com.vaccnow.vaccine.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reporting")
public class ReportingController {

    private final VaccinationService vaccinationService;

    public ReportingController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }



    @GetMapping(value = "/getAppliedVaccinationPerBranch/{branchId}")
    public ResponseEntity<VaccinationModelList> getAppliedVaccinationPerBranch(@PathVariable("branchId")Long branchId) {
        return ResponseEntity.ok(vaccinationService.getAppliedVaccinationPerBranch(branchId));
    }

    @PostMapping(value = "/getAppliedVaccinationWithinPeriod")
    public ResponseEntity<VaccinationModelList> getAppliedVaccinationWithinPeriod(@RequestBody GetAppliedVaccinationWithinPeriodRequestModel requestModel) {
        return ResponseEntity.ok(vaccinationService.getAppliedVaccinationWithinPeriod(requestModel));
    }

}
