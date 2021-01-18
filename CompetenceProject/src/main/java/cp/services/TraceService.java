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

    public Trace addTrace(Trace trace) {
        return traceRepository.insert(trace);
    }

    public List<Trace> addTrace(List<Trace> traces) {
        return traceRepository.insert(traces);
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
        return traces;
    }

    public List<Trace> getAllWithUserId(String userId) {
        return traceRepository.findByUserId(userId);
    }

    public List<Trace> getAllWithPOIId(String poiId) {
        return traceRepository.findByPoiId(poiId);
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
