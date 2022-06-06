package businesslayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
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
