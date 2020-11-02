package cp.controlers;

import cp.model.POI;
import cp.services.POIService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
Example JSON
{
    "name": "test2",
    "position": {
        "type": "Point",
        "coordinates": [10,10]
    },
    "description": "students",
    "type": "indoor"
}
 */
@RestController
@AllArgsConstructor
public class POIController {
    private final POIService poiService;

    @PostMapping("/poi")
    public void addPOI(@RequestBody POI poi) {
//        System.out.println(poi);
//        poi.setPosition(new GeoJsonPoint(5,10));
//        System.out.println(poi);
        poiService.addPOI(poi);
    }
}
