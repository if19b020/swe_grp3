package com.example.tourplanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
// maybe put in package "contracts" so class can be shared between different layers
public class TourLog {
    public TourLog(String date, String report, String distance, String time, String rating, String weather, String speed) {
        this(null, date, report, distance, time, rating, weather, speed);
    }

    public Integer Id;
    public String Date;
    public String Report;
    public String Distance;
    public String Time;
    public String Rating;
    public String Weather;
    public String Speed;

}
