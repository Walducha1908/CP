package cp.controlers;

import cp.services.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/simulation")
public class SimulationController {
    private final SimulationService simulationService;

    @GetMapping("/start")
    public ResponseEntity run() {
        try {
            simulationService.runSimulation();
            return ResponseEntity.ok("jest ok ale zajrzyj co robisz");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
