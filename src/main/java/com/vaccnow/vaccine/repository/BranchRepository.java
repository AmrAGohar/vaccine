package com.vaccnow.vaccine.repository;

import com.vaccnow.vaccine.entity.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {

}
