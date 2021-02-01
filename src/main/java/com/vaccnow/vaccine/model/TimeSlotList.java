package com.vaccnow.vaccine.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TimeSlotList {
    private List<TimeSlot> timeSlotList =new ArrayList<>();
}
