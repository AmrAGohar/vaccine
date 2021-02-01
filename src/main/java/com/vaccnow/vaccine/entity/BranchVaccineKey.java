package com.vaccnow.vaccine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class BranchVaccineKey implements Serializable {

    @Column(name = "branchId")
    Long branchId;

    @Column(name = "vaccineId")
    Long vaccineId;

    public BranchVaccineKey(Long branchId, Long vaccineId) {
        this.branchId = branchId;
        this.vaccineId = vaccineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchVaccineKey that = (BranchVaccineKey) o;
        return Objects.equals(branchId, that.branchId) &&
                Objects.equals(vaccineId, that.vaccineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, vaccineId);
    }
}
