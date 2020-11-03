package cp.services;

import cp.dto.POIDto;
import cp.exceptions.POINotFoundException;
import cp.exceptions.TraceNotFoundException;
import cp.model.POI;
import cp.model.Trace;
import cp.model.mappers.POIMapper;
import cp.repositories.TraceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TraceService {
    private final TraceRepository traceRepository;

    public void addTrace(Trace trace) {
        System.out.println(trace.getTimeOfEntry());
        traceRepository.insert(trace);
    }

    public Trace get(String id) throws TraceNotFoundException {
        if (traceRepository.findById(id).isPresent()) {
            log.debug(traceRepository.findById(id).get().toString());
            return traceRepository.findById(id).get();
        }
        log.error("Trace with id: " + id + " not found");
        throw new TraceNotFoundException();
    }

    public List<Trace> getAll() {

        List<Trace> traces = traceRepository.findAll();
        System.out.println(traces);
        return traces;
    }


    public Trace delete(String id) throws TraceNotFoundException {
        Trace poiFromDB = get(id);
        traceRepository.delete(poiFromDB);
        return poiFromDB;
    }

    public void deleteAll() {
        traceRepository.deleteAll();
    }

}
