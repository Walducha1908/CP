package cp.services;

import cp.controlers.TraceController;
import cp.model.POI;
import cp.model.Person;
import cp.model.Profile;
import cp.model.Trace;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class AnalysisService {
    private final TraceService traceService;
    private final POIService poiService;
    private final PersonService personService;

    /**
     * Analise traces/POIs
     * By frequent users, lengths of stay etc.
     */
    public void clusterPOIs() throws Exception {
        throw new Exception("Clustering not implemented");
    }

    /**
     * Rank POIs by ??
     */
    public void rankPOIs() throws Exception {
        throw new Exception("Ranking not implemented");
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
