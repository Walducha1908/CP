package cp.controlers;

import cp.exceptions.POINotFoundException;
import cp.exceptions.TraceNotFoundException;
import cp.model.Trace;
import cp.services.TraceService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TraceController {
    private final TraceService traceService;

    @PostMapping("/trace")
    public void add(@RequestBody Trace trace) {
        traceService.addTrace(trace);
    }

    @GetMapping("/trace/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(traceService.get(id));
        } catch (TraceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/trace")
    @ResponseBody
    public ResponseEntity getAll() {
        System.out.println(traceService.getAll());
        return ResponseEntity.ok(traceService.getAll());
    }

    @DeleteMapping("/traces")
    public ResponseEntity deleteAll() {
        traceService.deleteAll();
        return ResponseEntity.ok("Deleted all traces successfully");
    }
}
