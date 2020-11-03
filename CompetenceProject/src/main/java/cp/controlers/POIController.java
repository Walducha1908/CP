package cp.controlers;

import cp.dto.POIDto;
import cp.dto.PersonDto;
import cp.exceptions.POINotFoundException;
import cp.exceptions.PersonNotFoundException;
import cp.model.POI;
import cp.services.POIService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class POIController {
    private final POIService poiService;

    @PostMapping("/poi")
    @ResponseBody
    public ResponseEntity add(@RequestBody POI poi) {
        return ResponseEntity.ok(poiService.add(poi));
    }

    @GetMapping("/poi/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(poiService.get(id));
        } catch (POINotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/poi")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(poiService.getAll());
    }

    @PutMapping("/poi/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable String id, @RequestBody POIDto poiDto) {
        try {
            return ResponseEntity.ok(poiService.update(id, poiDto));
        } catch (POINotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/poi/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(poiService.delete(id));
        } catch (POINotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
