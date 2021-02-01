package com.vaccnow.vaccine.controller;

import com.vaccnow.vaccine.exception.NotExistVaccineException;
import com.vaccnow.vaccine.model.ScheduleVaccinationModel;
import com.vaccnow.vaccine.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccination")
public class VaccinationController {


    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @PostMapping(value = "/scheduleVaccination/{branchId}/{vaccineId}")
    public ResponseEntity<Long> scheduleVaccination(@PathVariable("branchId") Long branchId,
                                                      @PathVariable("vaccineId") Long vaccineId,
                                                      @RequestBody ScheduleVaccinationModel scheduleVaccinationModel) throws NotExistVaccineException {
        return ResponseEntity.ok(vaccinationService.scheduleVaccination(branchId, vaccineId,scheduleVaccinationModel));
    }

}
