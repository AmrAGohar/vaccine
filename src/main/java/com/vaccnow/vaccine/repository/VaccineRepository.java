package com.vaccnow.vaccine.repository;

import com.vaccnow.vaccine.entity.Vaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Long> {

}
