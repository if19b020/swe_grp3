package businesslayer;

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

    public String getFullDescription() {
        return "Name: \t\t" + getName() + "\nFrom: \t\t" + getFrom() + "\nTo: \t\t\t" + getTo() +
                "\nDistance: \t\t" + getDistance() + " km\nDescription: \t" + getDescription();
    }
}
