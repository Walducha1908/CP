package cp.controlers;

import cp.model.POI;
import cp.services.AnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/calculateVisited")
    @ResponseBody
    public ResponseEntity calculateMostVisited(POI givenPoi) {
        try {
            analysisService.calculateMostVisited(givenPoi);
            return ResponseEntity.ok("jest ok ale zajrzyj co robisz");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
