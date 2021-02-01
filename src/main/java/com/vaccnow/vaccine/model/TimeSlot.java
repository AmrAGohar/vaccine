package com.vaccnow.vaccine.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlot implements Comparable<TimeSlot> {
    private LocalDateTime time;
    private boolean isAvailable;

    public TimeSlot(LocalDateTime time, boolean isAvailable) {
        this.time = time;
        this.isAvailable = isAvailable;
    }

    @Override
    public int compareTo(TimeSlot o) {
        if(time.isAfter(o.getTime())){
            return 1;
        }else{
            return -1;
        }
    }
}
