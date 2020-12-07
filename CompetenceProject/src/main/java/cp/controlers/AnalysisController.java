package cp.controlers;

import cp.services.AnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/cluster/Rest")
    @ResponseBody
    public ResponseEntity clusterPOIsRest() {
        try {
            return ResponseEntity.ok(analysisService.clusterPOIRest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cluster/7000")
    @ResponseBody
    public ResponseEntity clusterPOIsLessThan7000() {
        try {
            return ResponseEntity.ok(analysisService.clusterPOIsLessThan7000());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cluster/10000")
    @ResponseBody
    public ResponseEntity clusterPOIsMoreThan10000() {
        try {
            return ResponseEntity.ok(analysisService.clusterPOIsMoreThan10000());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/rankPOI/visits")
    @ResponseBody
    public ResponseEntity rankPOIsVisits ()   {
        try {
            return ResponseEntity.ok(analysisService.rankPOIsVisits());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/rankPOI/time")
    @ResponseBody
    public ResponseEntity rankPOIsTime ()   {
        try {
            return ResponseEntity.ok(analysisService.rankPOIsTimes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/calculateVisited/{id}")
    @ResponseBody
    public ResponseEntity calculateMostVisited(@PathVariable String id) {
        try {
            return ResponseEntity.ok(analysisService.calculateMostVisited(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/calculateVisited/students/{id}")
    @ResponseBody
    public ResponseEntity calculateMostVisitedByStudents(@PathVariable String id) {
        try {
            return ResponseEntity.ok(analysisService.calculateMostVisitedByStudents(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/calculateVisited/teachers/{id}")
    @ResponseBody
    public ResponseEntity calculateMostVisitedByTeachers(@PathVariable String id) {
        try {
            return ResponseEntity.ok(analysisService.calculateMostVisitedByTeachers(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/calculateVisited/servicestaff/{id}")
    @ResponseBody
    public ResponseEntity calculateMostVisitedByServiceStaff(@PathVariable String id) {
        try {
            return ResponseEntity.ok(analysisService.calculateMostVisitedByServiceStaff(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
