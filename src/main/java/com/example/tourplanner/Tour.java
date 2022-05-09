package com.example.tourplanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class Tour {
    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private int distance;
    private int estimatedTime;
    private String routeInformation;

    private List<TourLog> tourLogs;


}
