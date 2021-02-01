package com.vaccnow.vaccine.model;

import lombok.Data;

@Data
public class VaccineModel {
    private Long id;
    private String name;
    private String description;
    private int availability;
}
