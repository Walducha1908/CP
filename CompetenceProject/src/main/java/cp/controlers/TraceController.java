package cp.controlers;

import cp.model.Trace;
import cp.services.TraceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TraceController {
    private final TraceService traceService;

    @PostMapping("/trace")
    public void addPOI(@RequestBody Trace trace) {
        traceService.addTrace(trace);
    }

}
