package com.vaccnow.vaccine.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BranchModelList {
    private List<BranchModel> branches = new ArrayList<>();
}
