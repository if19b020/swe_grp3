package com.example.tourplanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Tour {
    public Integer Id;
    public String Name;
    public String Description;
    public String Distance;
    public String From;
    public String To;

}
