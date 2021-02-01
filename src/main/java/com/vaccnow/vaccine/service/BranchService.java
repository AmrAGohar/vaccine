package com.vaccnow.vaccine.service;

import com.vaccnow.vaccine.entity.Branch;
import com.vaccnow.vaccine.entity.BranchVaccine;
import com.vaccnow.vaccine.entity.Vaccination;
import com.vaccnow.vaccine.entity.Vaccine;
import com.vaccnow.vaccine.exception.NotExistVaccineException;
import com.vaccnow.vaccine.model.*;
import com.vaccnow.vaccine.repository.BranchRepository;
import com.vaccnow.vaccine.repository.BranchVaccineRepository;
import com.vaccnow.vaccine.repository.VaccinationRepository;
import com.vaccnow.vaccine.util.Constants;
import com.vaccnow.vaccine.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BranchService {

    @Autowired
    private final BranchRepository branchRepository;

    @Autowired
    private final BranchVaccineRepository branchVaccineRepository;

    @Autowired
    private final VaccinationRepository vaccinationRepository;


    public BranchService(BranchRepository branchRepository, BranchVaccineRepository branchVaccineRepository, VaccinationRepository vaccinationRepository) {
        this.branchRepository = branchRepository;
        this.branchVaccineRepository = branchVaccineRepository;
        this.vaccinationRepository = vaccinationRepository;
    }

    public BranchModelList getAllBranches() {
        BranchModelList result = new BranchModelList();
        List<Branch> branches = (List<Branch>) branchRepository.findAll();
        for (Branch branch : branches) {
            result.getBranches().add(constructBranchModelFromEntity(branch));
        }
        return result;
    }

    public VaccineModelList getAvailableVaccinesPerBranch(Long branchId) {
        VaccineModelList result = new VaccineModelList();
        List<BranchVaccine> branchVaccines = branchVaccineRepository.findByBranch_idAndAvailableCountGreaterThan(branchId, 0);
        for (BranchVaccine branchVaccine : branchVaccines) {
            result.getVaccines().add(constructVaccineModelFromEntity(branchVaccine.getVaccine(), branchVaccine.getAvailableCount()));
        }
        return result;
    }

    public VaccineModel getSpecificAvailabilityPerBranch(Long branchId, Long vaccineId) throws NotExistVaccineException {
        BranchVaccine branchVaccine = branchVaccineRepository.findByBranch_idAndVaccine_id(branchId, vaccineId);
        if (branchVaccine == null) {
            throw new NotExistVaccineException(Constants.EXCEPTION_VACCINE_NOT_FOUND);
        }
        return constructVaccineModelFromEntity(branchVaccine.getVaccine(), branchVaccine.getAvailableCount());
    }

    private VaccineModel constructVaccineModelFromEntity(Vaccine vaccine, Integer availability) {
        VaccineModel vaccineModel = new VaccineModel();
        vaccineModel.setId(vaccine.getId());
        vaccineModel.setName(vaccine.getName());
        vaccineModel.setDescription(vaccine.getDescription());
        vaccineModel.setAvailability(availability);
        return vaccineModel;
    }

    private BranchModel constructBranchModelFromEntity(Branch branch) {
        BranchModel branchModel = new BranchModel();
        branchModel.setId(branch.getId());
        branchModel.setName(branch.getName());
        return branchModel;
    }

    public TimeSlotList getAvailableTimePerBranch(Long branchId, LocalDate day) {
        if (day.isBefore(LocalDate.now())) {
            return new TimeSlotList();
        }

        TimeSlotList result = new TimeSlotList();
        Map<LocalDateTime, Boolean> resultMap = new HashMap<>();
        LocalDateTime start = day.atTime(LocalTime.of(9, 0));
        LocalDateTime end = day.atTime(LocalTime.of(17, 15));

        List<Vaccination> vaccinations = vaccinationRepository.findByBranch_idAndTimeSlotBetween(branchId, TimeUtils.convertToDateViaSqlTimestamp(start), TimeUtils.convertToDateViaSqlTimestamp(end));
        for (LocalDateTime localDateTime = start; localDateTime.isBefore(end); localDateTime = localDateTime.plusMinutes(15)) {
            resultMap.put(localDateTime, true);
        }

        for (Vaccination vaccination : vaccinations) {
            resultMap.put(TimeUtils.convertToLocalDateTimeViaSqlTimestamp(vaccination.getTimeSlot()), false);
        }

        resultMap.forEach((k, v) -> {
            result.getTimeSlotList().add(new TimeSlot(k, v));
        });

        Collections.sort(result.getTimeSlotList());

        return result;
    }


    public Branch getBranch(Long branchId){
        return branchRepository.findById(branchId).orElse(null);
    }
}
