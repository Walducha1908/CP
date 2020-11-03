package cp.services;

import cp.model.Trace;
import cp.repositories.TraceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TraceService {
    private final TraceRepository traceRepository;

    public void addTrace(Trace trace) {
        traceRepository.insert(trace);
    }
}
