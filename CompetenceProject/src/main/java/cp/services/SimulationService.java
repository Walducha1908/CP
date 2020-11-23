package cp.services;

import cp.model.POI;
import cp.model.Person;
import cp.model.Profile;
import cp.model.Trace;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.distribution.WeibullDistribution;
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

    /**
     * Method responsible for running simulation.
     *
     * @throws Exception
     */
    public void runSimulation() throws Exception {
        traceService.deleteAll();
        List<Person> personList = personService.getAll();
        List<POI> poiList = poiService.getAll();

        for (Person person : personList) {
            List<Trace> traces = getTraceList(person, poiList);
            traces.forEach(trace -> traceService.addTrace(trace));
        }

    }

    private List<Trace> getTraceList(Person person, List<POI> poiList) {
        List<Trace> result = new LinkedList<>();
        LocalDateTime beginDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0, 0, 0));
        Collections.shuffle(poiList);
        POI currentPoint = poiList.get(0);
        for (int i = 0; i < 20; i++) {
            POI newPoint = getNextPOI(currentPoint, poiList, person, beginDate);
            int timeTravel = getRandomMinutesTravel();
            int timeOnPlace = getRandomMinutesOnPlace();
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

    private int getRandomMinutesTravel() {
        return ThreadLocalRandom.current().nextInt(3, 20 + 1);
    }

    private int getRandomMinutesOnPlace() {
        return ThreadLocalRandom.current().nextInt(15, 90 + 1);
    }
}
