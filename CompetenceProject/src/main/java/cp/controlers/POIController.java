package cp.controlers;

import cp.model.POI;
import cp.services.POIService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class POIController {
    private final POIService poiService;

    @PostMapping("/poi")
    public void addPOI(@RequestBody POI poi) {
        poiService.addPOI(poi);
    }
}
