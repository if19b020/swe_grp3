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
    private Date date;
    private int duration;
    private String comment;
    private int difficulty; // 1 = easy, 5 = difficult
    private int time;
    private int rating;

}
