package cp.services;

import cp.model.POI;
import cp.model.Person;
import cp.model.Profile;
import cp.model.Trace;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
@Slf4j
public class SimulationService {
    private final TraceService traceService;
    private final POIService poiService;
    private final PersonService personService;
    private HashMap<String, Pair<Integer, Integer>> times_stay;
    private List<Pair<Integer, Integer>> times_travel;

    /**
     * Method responsible for running simulation.
     *
     * @throws Exception
     */
    public void runSimulation() throws Exception {
        traceService.deleteAll();
        List<Person> personList = personService.getAll();
        List<POI> poiList = poiService.getAll();
        times_stay = new HashMap<>();
        times_travel = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            times_travel.add(new Pair<>(convertValue(ThreadLocalRandom.current().nextGaussian(), 2, 4), convertValue(ThreadLocalRandom.current().nextGaussian(), 15, 25)));
        }
        poiList.forEach(item -> times_stay.put(item.getId(), new Pair<>(convertValue(ThreadLocalRandom.current().nextGaussian(), 15, 25), convertValue(ThreadLocalRandom.current().nextGaussian(), 80, 100))));

        for (Person person : personList) {
            List<Trace> traces = getTraceList(person, poiList);
            traces.forEach(traceService::addTrace);
        }
    }

    private List<Trace> getTraceList(Person person, List<POI> poiList) {
        List<Trace> result = new LinkedList<>();
        LocalDateTime beginDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0, 0, 0));
        Collections.shuffle(poiList);
        POI currentPoint = poiList.get(0);
        Collections.shuffle(times_travel);
        for (int i = 0; i < 20; i++) {
            POI newPoint = getNextPOI(currentPoint, poiList, person, beginDate);
            int timeTravel = getRandomMinutesTravel(times_travel.get(i));
            int timeOnPlace = getRandomMinutesOnPlace(times_stay.get(newPoint.getId()));
            beginDate = beginDate.plusMinutes(timeTravel);

            Trace trace = new Trace();
            trace.setPoiId(currentPoint.getId());
            trace.setUserId(person.getId());
            trace.setTimeOfEntry(beginDate);
            beginDate = beginDate.plusMinutes(timeOnPlace);
            trace.setTimeOfExit(beginDate);
            result.add(trace);
            currentPoint = newPoint;
            log.info(trace.toString());
            if (beginDate.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0, 0, 0)))) {
                break;
            }
        }

        return result;
    }

    private POI getNextPOI(POI currentPOI, List<POI> poiList, Person person, LocalDateTime dateTime) {
        HashMap<POI, Double> poiProbabilities = new HashMap<>();
        int maxRandValue = 0;
        for (POI poi : poiList) {
            if (!currentPOI.getId().equals(poi.getId())) {
                double probability = calculatePOIProbability(poi, person.getProfile(), dateTime);
                poiProbabilities.put(poi, probability);
                maxRandValue += (int) (probability * 1000);
            }
        }

        Random random = new Random();
        int randomProbabilityValue = random.nextInt(maxRandValue + 1);

        int incrementingProbability = 0;
        for (POI poi : poiList) {
            if (!currentPOI.getId().equals(poi.getId())) {
                incrementingProbability += (int) (poiProbabilities.get(poi) * 1000);
                if (randomProbabilityValue <= incrementingProbability) {
                    return poi;
                }
            }
        }
        return null;
    }

    private double calculatePOIProbability(POI poi, Profile profile, LocalDateTime dateTime) {
        WeibullDistribution weibullDistribution;
        if (profile == Profile.students) {
            weibullDistribution = new WeibullDistribution(poi.getStudentA(), poi.getStudentB());
        } else if (profile == Profile.teachers) {
            weibullDistribution = new WeibullDistribution(poi.getTeacherA(), poi.getTeacherB());
        } else if (profile == Profile.serviceStaff) {
            weibullDistribution = new WeibullDistribution(poi.getStuffA(), poi.getStuffB());
        } else {
            weibullDistribution = new WeibullDistribution(1, 1);
        }

        double time = dateTime.getHour();
        time += (double) dateTime.getMinute() / 60.0;
        return weibullDistribution.density(time);
    }

    private int getRandomMinutesTravel(Pair<Integer, Integer> min_max) {
        return convertValue(ThreadLocalRandom.current().nextGaussian(), min_max.getValue0(), min_max.getValue1());
        //return ThreadLocalRandom.current().nextInt(3, 20 + 1);
    }

    private int getRandomMinutesOnPlace(Pair<Integer, Integer> min_max) {
        return convertValue(ThreadLocalRandom.current().nextGaussian(), min_max.getValue0(), min_max.getValue1());
        //return ThreadLocalRandom.current().nextInt(15, 90 + 1);
    }

    private int convertValue(double old_value, double new_min, double new_max) {
        double old_min = -1;
        double old_max = 1;
        double new_value = ((old_value - old_min) / (old_max - old_min)) * (new_max - new_min) + new_min;
        return (int) new_value;
    }
}
