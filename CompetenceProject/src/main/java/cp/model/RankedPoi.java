package cp.model;

public class RankedPoi {
    String Name;
    Integer Visits;

    public RankedPoi(String name, Integer visits) {
        this.Name = name;
        this.Visits = visits;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getVisits() {
        return Visits;
    }

    public void setVisits(Integer visits) {
        this.Visits = visits;
    }

}


