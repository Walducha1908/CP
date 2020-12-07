package cp.model;

import java.time.Duration;

public class RankedPoiTimeSpent {
    String Name;
    long Minutes;

    public RankedPoiTimeSpent(String name, long minutes) {
        Name = name;
        Minutes = minutes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public long getMinutes() {
        return Minutes;
    }

    public void setMinutes(long minutes) {
        this.Minutes = minutes;
    }
}
