package cp.services;

import cp.model.POI;
import cp.model.Person;
import cp.model.Trace;
import cp.repositories.POIRepository;
import cp.repositories.TraceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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

        //throw new Exception("Simulation not implemented");
    }

    private List<Trace> getTraceList(Person person, List<POI> poiList) {
        List<Trace> result = new LinkedList<>();
        LocalDateTime beginDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0, 0, 0));
        POI currentPoint = getRandomPOI(poiList);
        for (int i = 0; i < 20; i++) {
            POI newPoint = getRandomPOI(poiList);
            while (newPoint.equals(currentPoint)) {
                newPoint = getRandomPOI(poiList);
            }
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
            if (beginDate.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0, 0, 0)))) {
                break;
            }
        }

        return result;
    }

    private int getRandomMinutesTravel() {
        return ThreadLocalRandom.current().nextInt(3, 20 + 1);
    }

    private int getRandomMinutesOnPlace() {
        return ThreadLocalRandom.current().nextInt(15, 90 + 1);
    }

    private POI getRandomPOI(List<POI> poiList) {
        Random rand = new Random();
        return poiList.get(rand.nextInt(poiList.size()));
    }
}
