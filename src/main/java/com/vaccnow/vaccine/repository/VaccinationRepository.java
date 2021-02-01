package com.vaccnow.vaccine.repository;

import com.vaccnow.vaccine.entity.Vaccination;
import com.vaccnow.vaccine.entity.enums.VaccinationStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VaccinationRepository extends CrudRepository<Vaccination, Long> {
    List<Vaccination> findByBranch_idAndTimeSlotBetween(Long branchId, Date start, Date end);
    List<Vaccination> findByBranch_idAndStatusEquals(Long branchId, VaccinationStatus status);
    List<Vaccination> findByStatusEqualsAndTimeSlotBetween(VaccinationStatus status, Date start, Date end);

}
