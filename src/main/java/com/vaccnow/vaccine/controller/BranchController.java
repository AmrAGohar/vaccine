package com.vaccnow.vaccine.controller;

import com.vaccnow.vaccine.exception.NotExistVaccineException;
import com.vaccnow.vaccine.model.*;
import com.vaccnow.vaccine.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "/getAllBranches")
    public ResponseEntity<BranchModelList> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping(value = "/getAvailableVaccinesPerBranch/{branchId}")
    public ResponseEntity<VaccineModelList> getAvailableVaccinesPerBranch(@PathVariable("branchId") Long branchId) {
        return ResponseEntity.ok(branchService.getAvailableVaccinesPerBranch(branchId));
    }

    @GetMapping(value = "/getSpecificAvailabilityPerBranch/{branchId}/{vaccineId}")
    public ResponseEntity<VaccineModel> getSpecificAvailabilityPerBranch(@PathVariable("branchId") Long branchId, @PathVariable("vaccineId") Long vaccineId) throws NotExistVaccineException {
        return ResponseEntity.ok(branchService.getSpecificAvailabilityPerBranch(branchId, vaccineId));
    }

    @PostMapping(value = "/getAvailableTimePerBranch")
    public ResponseEntity<TimeSlotList> getAvailableTimePerBranch(@RequestBody GetAvailableTimePerBranchRequestModel model) throws NotExistVaccineException {
        return ResponseEntity.ok(branchService.getAvailableTimePerBranch(model.getBranchId(),model.getDay()));
    }

}
