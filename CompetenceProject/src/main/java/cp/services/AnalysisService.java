package cp.services;

import cp.helpers.PoiCompareVisits;
import cp.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
public class AnalysisService {
    private final TraceService traceService;
    private final POIService poiService;
    private final PersonService personService;

    /**
     * Rank POIs by the amount of time spent in poi
     * @return
     */
    public LinkedList<RankedPoiTimeSpent> clusterPOILongestTime() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoiTimeSpent> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            long time = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    if(time < Duration.between(traces.get(j).getTimeOfEntry(), traces.get(j).getTimeOfExit()).toMinutes()) {
                        time = Duration.between(traces.get(j).getTimeOfEntry(), traces.get(j).getTimeOfExit()).toMinutes();
                    }
                }
            }
            list.add(new RankedPoiTimeSpent(pois.get(i).getName(), time));
        }

        return list;
    }

    /**
     * Analise traces/POIs
     * By frequent users, more than 7000 users per day but less than 10000
     * @return
     */
    public LinkedList<RankedPoi> clusterPOIRest() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoi> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            int count = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    count++;
                }
            }
            list.add(new RankedPoi(pois.get(i).getName(), count));
        }

        PoiCompareVisits poiCompareVisits = new PoiCompareVisits();
        Collections.sort(list, poiCompareVisits);

        LinkedList<RankedPoi> result = new LinkedList<>();
        for(RankedPoi rp : list){
            if(rp.getVisits() > 7000 && rp.getVisits() < 10000){result.add(rp);}
        }

        return result;
    }

    /**
     * Analise traces/POIs
     * By frequent users, less than 7000 users per day
     * @return
     */
    public LinkedList<RankedPoi> clusterPOIsLessThan7000() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoi> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            int count = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    count++;
                }
            }
            list.add(new RankedPoi(pois.get(i).getName(), count));
        }

        PoiCompareVisits poiCompareVisits = new PoiCompareVisits();
        Collections.sort(list, poiCompareVisits);

        LinkedList<RankedPoi> result = new LinkedList<>();
        for(RankedPoi rp : list){
            if(rp.getVisits() <= 7000){result.add(rp);}
        }

        return result;
    }

    /**
     * Analise traces/POIs
     * By frequent users, more than 10000 users per day
     * @return
     */
    public LinkedList<RankedPoi> clusterPOIsMoreThan10000() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoi> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            int count = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    count++;
                }
            }
            list.add(new RankedPoi(pois.get(i).getName(), count));
        }

        PoiCompareVisits poiCompareVisits = new PoiCompareVisits();
        Collections.sort(list, poiCompareVisits);

        LinkedList<RankedPoi> result = new LinkedList<>();
        for(RankedPoi rp : list){
            if(rp.getVisits() >= 10000){result.add(rp);}
        }

        return result;
    }

    /**
     * Rank POIs by the number of visits per day
     * @return
     */
    public LinkedList<RankedPoi> rankPOIsVisits() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoi> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            int count = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    count++;
                }
            }
            list.add(new RankedPoi(pois.get(i).getName(), count));
        }

        PoiCompareVisits poiCompareVisits = new PoiCompareVisits();
        Collections.sort(list, poiCompareVisits);

        return list;
    }

    /**
     * Rank POIs by the amount of time spent in poi
     * @return
     */
    public LinkedList<RankedPoiTimeSpent> rankPOIsTimes() {
        List<Trace> traces = traceService.getAll();
        List<POI> pois = poiService.getAll();
        LinkedList<RankedPoiTimeSpent> list = new LinkedList<>();
        for(int i=0; i<pois.size(); i++){
            System.out.println(i);
            long time = 0;
            int count = 0;
            for(int j =0; j<traces.size(); j++){
                if(pois.get(i).getId().equals(traces.get(j).getPoiId())){
                    count++;
                    time += Duration.between(traces.get(j).getTimeOfEntry(), traces.get(j).getTimeOfExit()).toMinutes();
                }
            }
            list.add(new RankedPoiTimeSpent(pois.get(i).getName(), time/count));
        }

        return list;
    }

    /**
     * For givenPOI ID calculate numbers of visits in other pois
     *
     * @param poiID
     * @return most visited POI
     * @throws Exception
     */
    public Map calculateMostVisited(String poiID) throws Exception {
        LinkedList<String> ids = new LinkedList<>();

        List<Trace> tracesForPOI = traceService.getAllWithPOIId(poiID);

        for (int i = 0; i < tracesForPOI.size(); i++) {
            System.out.println("poiTrace: " + i);
            String currentUserId = tracesForPOI.get(i).getUserId();
            LocalDateTime currentTime = tracesForPOI.get(i).getTimeOfEntry();

            List<Trace> tracesForUser = traceService.getAllWithUserId(currentUserId);
            for (int j = 0; j < tracesForUser.size() - 1; j++) {
                if (tracesForUser.get(j).getPoiId().equals(poiID) && tracesForUser.get(j).getTimeOfEntry().isEqual(currentTime)) {
                    //tutaj mamy trace z ID następnego poi do którego poszedł user z poiID
                    Trace t = tracesForUser.get(j + 1);
                    ids.push(t.getPoiId());
                }
            }
        }
        //initializing map with pois and number of visits
        var map = new HashMap<String, Integer>();
        List<POI> pois = poiService.getAll();
        POI currentPOI = poiService.get(poiID);
        pois.remove(currentPOI);

        for (POI poi : pois) {
            int count = 0;
            for (String id : ids) {
                if (poi.getId().equals(id)) {
                    count++;
                }
            }
            map.put(poi.getName(), count);
        }

        System.out.println(map.toString());

        return map;
    }

    /**
     * For givenPOI ID calculate numbers of visits in other pois for students
     *
     * @param poiID
     * @return most visited POI
     * @throws Exception
     */
    public Map calculateMostVisitedByStudents(String poiID) throws Exception {
        LinkedList<String> ids = new LinkedList<>();

        List<Trace> tracesForPOI = traceService.getAllWithPOIId(poiID);

        for (int i = 0; i < tracesForPOI.size(); i++) {
            System.out.println("poiTrace: " + i);
            String currentUserId = tracesForPOI.get(i).getUserId();
            LocalDateTime currentTime = tracesForPOI.get(i).getTimeOfEntry();
            if (personService.get(currentUserId).getProfile() == Profile.students) {
                List<Trace> tracesForUser = traceService.getAllWithUserId(currentUserId);
                for (int j = 0; j < tracesForUser.size() - 1; j++) {
                    if (tracesForUser.get(j).getPoiId().equals(poiID) && tracesForUser.get(j).getTimeOfEntry().isEqual(currentTime)) {
                        //tutaj mamy trace z ID następnego poi do którego poszedł user z poiID
                        Trace t = tracesForUser.get(j + 1);
                        ids.push(t.getPoiId());
                    }
                }
            }
        }
        //initializing map with pois and number of visits
        var map = new HashMap<String, Integer>();
        List<POI> pois = poiService.getAll();
        POI currentPOI = poiService.get(poiID);
        pois.remove(currentPOI);

        for (POI poi : pois) {
            int count = 0;
            for (String id : ids) {
                if (poi.getId().equals(id)) {
                    count++;
                }
            }
            map.put(poi.getName(), count);
        }

        System.out.println(map.toString());

        return map;
    }

    /**
     * For givenPOI ID calculate numbers of visits in other pois for teachers
     *
     * @param poiID
     * @return most visited POI
     * @throws Exception
     */
    public Map calculateMostVisitedByTeachers(String poiID) throws Exception {
        LinkedList<String> ids = new LinkedList<>();

        List<Trace> tracesForPOI = traceService.getAllWithPOIId(poiID);

        for (int i = 0; i < tracesForPOI.size(); i++) {
            System.out.println("poiTrace: " + i);
            String currentUserId = tracesForPOI.get(i).getUserId();
            LocalDateTime currentTime = tracesForPOI.get(i).getTimeOfEntry();
            if (personService.get(currentUserId).getProfile() == Profile.teachers) {
                List<Trace> tracesForUser = traceService.getAllWithUserId(currentUserId);
                for (int j = 0; j < tracesForUser.size() - 1; j++) {
                    if (tracesForUser.get(j).getPoiId().equals(poiID) && tracesForUser.get(j).getTimeOfEntry().isEqual(currentTime)) {
                        //tutaj mamy trace z ID następnego poi do którego poszedł user z poiID
                        Trace t = tracesForUser.get(j + 1);
                        ids.push(t.getPoiId());
                    }
                }
            }
        }
        //initializing map with pois and number of visits
        var map = new HashMap<String, Integer>();
        List<POI> pois = poiService.getAll();
        POI currentPOI = poiService.get(poiID);
        pois.remove(currentPOI);

        for (POI poi : pois) {
            int count = 0;
            for (String id : ids) {
                if (poi.getId().equals(id)) {
                    count++;
                }
            }
            map.put(poi.getName(), count);
        }

        System.out.println(map.toString());

        return map;
    }

    /**
     * For givenPOI ID calculate numbers of visits in other pois for service staff
     *
     * @param poiID
     * @return most visited POI
     * @throws Exception
     */
    public Map calculateMostVisitedByServiceStaff(String poiID) throws Exception {
        LinkedList<String> ids = new LinkedList<>();

        List<Trace> tracesForPOI = traceService.getAllWithPOIId(poiID);

        for (int i = 0; i < tracesForPOI.size(); i++) {
            System.out.println("poiTrace: " + i);
            String currentUserId = tracesForPOI.get(i).getUserId();
            LocalDateTime currentTime = tracesForPOI.get(i).getTimeOfEntry();
            if (personService.get(currentUserId).getProfile() == Profile.serviceStaff) {
                List<Trace> tracesForUser = traceService.getAllWithUserId(currentUserId);
                for (int j = 0; j < tracesForUser.size() - 1; j++) {
                    if (tracesForUser.get(j).getPoiId().equals(poiID) && tracesForUser.get(j).getTimeOfEntry().isEqual(currentTime)) {
                        //tutaj mamy trace z ID następnego poi do którego poszedł user z poiID
                        Trace t = tracesForUser.get(j + 1);
                        ids.push(t.getPoiId());
                    }
                }
            }
        }
        //initializing map with pois and number of visits
        var map = new HashMap<String, Integer>();
        List<POI> pois = poiService.getAll();
        POI currentPOI = poiService.get(poiID);
        pois.remove(currentPOI);

        for (POI poi : pois) {
            int count = 0;
            for (String id : ids) {
                if (poi.getId().equals(id)) {
                    count++;
                }
            }
            map.put(poi.getName(), count);
        }

        System.out.println(map.toString());

        return map;
    }
}
