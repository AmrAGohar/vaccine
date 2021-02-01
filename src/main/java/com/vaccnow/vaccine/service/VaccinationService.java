package com.vaccnow.vaccine.service;

import com.vaccnow.vaccine.entity.Citizen;
import com.vaccnow.vaccine.entity.Vaccination;
import com.vaccnow.vaccine.entity.Vaccine;
import com.vaccnow.vaccine.entity.enums.PaymentMethod;
import com.vaccnow.vaccine.entity.enums.VaccinationStatus;
import com.vaccnow.vaccine.model.GetAppliedVaccinationWithinPeriodRequestModel;
import com.vaccnow.vaccine.model.ScheduleVaccinationModel;
import com.vaccnow.vaccine.model.VaccinationModel;
import com.vaccnow.vaccine.model.VaccinationModelList;
import com.vaccnow.vaccine.repository.VaccinationRepository;
import com.vaccnow.vaccine.repository.VaccineRepository;
import com.vaccnow.vaccine.util.TimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;
    private final BranchService branchService;


    public VaccinationService(VaccinationRepository vaccinationRepository, VaccineRepository vaccineRepository, BranchService branchService) {
        this.vaccinationRepository = vaccinationRepository;
        this.vaccineRepository = vaccineRepository;
        this.branchService = branchService;
    }

    public Long scheduleVaccination(Long branchId, Long vaccineId, ScheduleVaccinationModel scheduleVaccinationModel) {
        Vaccination vaccination = new Vaccination();
        vaccination.setBranch(branchService.getBranch(branchId));
        vaccination.setVaccine(getVaccine(vaccineId));
        vaccination.setCitizen(constructCitizenObject(scheduleVaccinationModel));
        vaccination.setStatus(VaccinationStatus.REQUESTED);
        vaccination.setPaymentMethod(PaymentMethod.CASH);
        vaccination.setTimeSlot(TimeUtils.convertToDateViaSqlTimestamp(scheduleVaccinationModel.getTimeSlot()));
        return vaccinationRepository.save(vaccination).getId();
    }

    private Citizen constructCitizenObject(ScheduleVaccinationModel scheduleVaccinationModel) {
        Citizen citizen = new Citizen();
        citizen.setEmail(scheduleVaccinationModel.getEmail());
        citizen.setName(scheduleVaccinationModel.getName());
        citizen.setNationalId(scheduleVaccinationModel.getNationalId());
        return citizen;
    }

    public Vaccine getVaccine(Long vaccineId) {
        return vaccineRepository.findById(vaccineId).orElse(null);

    }

    public VaccinationModelList getAppliedVaccinationPerBranch(Long branchId) {
        VaccinationModelList result = new VaccinationModelList();
        List<Vaccination> vaccinationList = vaccinationRepository.findByBranch_idAndStatusEquals(branchId, VaccinationStatus.APPLIED);
        for (Vaccination vaccination : vaccinationList) {
            result.getVaccinationModelList().add(constructVaccinationModel(vaccination));
        }
        return result;
    }

    private VaccinationModel constructVaccinationModel(Vaccination vaccination) {
        VaccinationModel vaccinationModel = new VaccinationModel();
        vaccinationModel.setBranchId(vaccination.getBranch().getId());
        vaccinationModel.setVaccineId(vaccination.getVaccine().getId());
        vaccinationModel.setCitizenId(vaccination.getCitizen().getId());
        vaccinationModel.setId(vaccination.getId());
        vaccinationModel.setPaymentMethod(vaccination.getPaymentMethod());
        vaccinationModel.setStatus(vaccination.getStatus());
        vaccinationModel.setTimeSlot(TimeUtils.convertToLocalDateTimeViaSqlTimestamp(vaccination.getTimeSlot()));
        return vaccinationModel;
    }

    public VaccinationModelList getAppliedVaccinationWithinPeriod(GetAppliedVaccinationWithinPeriodRequestModel requestModel) {
        VaccinationModelList result = new VaccinationModelList();
        Date startDate =getStartDate(requestModel.getStart());
        Date endDate = getEndDate(requestModel);

        List<Vaccination> vaccinationList = vaccinationRepository.findByStatusEqualsAndTimeSlotBetween(requestModel.getStatus(),startDate,endDate);
        for (Vaccination vaccination : vaccinationList) {
            result.getVaccinationModelList().add(constructVaccinationModel(vaccination));
        }
        return result;
    }

    private Date getEndDate(GetAppliedVaccinationWithinPeriodRequestModel requestModel) {
        if(requestModel.getEnd() ==null){
            return TimeUtils.convertToDateViaSqlTimestamp(requestModel.getStart().atTime(LocalTime.MAX));
        }
        return TimeUtils.convertToDateViaSqlTimestamp(requestModel.getEnd().atTime(LocalTime.MAX));
    }

    private Date getStartDate(LocalDate start) {
        return TimeUtils.convertToDateViaSqlTimestamp(start.atTime(LocalTime.MIDNIGHT));
    }
}
