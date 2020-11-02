package cp.services;

import cp.model.POI;
import cp.repositories.POIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class POIService {
    private final POIRepository poiRepository;

    public void addPOI(POI poi) {
        poiRepository.insert(poi);
    }
}
