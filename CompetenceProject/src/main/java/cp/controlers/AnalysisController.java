package cp.controlers;

import cp.model.POI;
import cp.services.AnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/cluster")
    @ResponseBody
    public ResponseEntity clusterPOIs () {
        try {
            analysisService.clusterPOIs();
            return ResponseEntity.ok("jest ok ale zajrzyj co robisz");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/rankPOI")
    @ResponseBody
    public ResponseEntity rankPOIs ()   {
        try {
            analysisService.rankPOIs();
            return ResponseEntity.ok("jest ok ale zajrzyj co robisz");
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
