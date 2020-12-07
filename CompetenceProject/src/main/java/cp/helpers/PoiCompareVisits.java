package cp.helpers;

import cp.model.RankedPoi;

import java.util.Comparator;

public class PoiCompareVisits implements Comparator<RankedPoi>
{
    @Override
    public int compare(RankedPoi o1, RankedPoi o2) {
        if (o1.getVisits() > o2.getVisits()) return -1;
        if (o1.getVisits() < o2.getVisits()) return 1;
        else return 0;
    }
}