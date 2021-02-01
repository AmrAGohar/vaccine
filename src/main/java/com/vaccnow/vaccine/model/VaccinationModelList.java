package com.vaccnow.vaccine.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VaccinationModelList {
    private List<VaccinationModel> vaccinationModelList = new ArrayList<>();
}
