package com.example.tourplanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    public Tour(String name, String from, String to, String description) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.description = description;
    }

    


}
