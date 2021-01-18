package cp.helpers;

import cp.model.RankedPoi;
import cp.model.RankedPoiTimeSpent;

import java.util.Comparator;

public class PoiCompareTime implements Comparator<RankedPoiTimeSpent>
{
    @Override
    public int compare(RankedPoiTimeSpent o1, RankedPoiTimeSpent o2) {
        if (o1.getMinutes() > o2.getMinutes()) return -1;
        if (o1.getMinutes() < o2.getMinutes()) return 1;
        else return 0;
    }
}