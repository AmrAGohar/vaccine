package com.vaccnow.vaccine.repository;

import com.vaccnow.vaccine.entity.BranchVaccine;
import com.vaccnow.vaccine.entity.BranchVaccineKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchVaccineRepository extends CrudRepository<BranchVaccine, BranchVaccineKey> {
    List<BranchVaccine> findByBranch_idAndAvailableCountGreaterThan(Long branchId,Integer count);
    BranchVaccine findByBranch_idAndVaccine_id(Long branchId,Long vaccineId);
}
