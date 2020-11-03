package cp.services;

import cp.dto.POIDto;
import cp.exceptions.POINotFoundException;
import cp.exceptions.PersonNotFoundException;
import cp.model.POI;
import cp.model.Person;
import cp.model.mappers.POIMapper;
import cp.model.mappers.PersonMapper;
import cp.repositories.POIRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class POIService {
    private final POIRepository poiRepository;

    public POI add(POI poi) {
        System.out.println(poi);
        return poiRepository.insert(poi);
    }

    public POI get(String id) throws POINotFoundException {
        if (poiRepository.findById(id).isPresent()) {
            return poiRepository.findById(id).get();
        }
        log.error("POI with id: " + id + " not found");
        throw new POINotFoundException();
    }

    public List<POI> getAll() {
        return poiRepository.findAll();
    }

    public POI update(String id, POIDto poiDto) throws POINotFoundException {
        POI poiFromDB = get(id);
        POI poiToSave = POIMapper.mapFromDto(poiDto);
        poiToSave.setId(poiFromDB.getId());
        return poiRepository.save(poiToSave);
    }

    public POI delete(String id) throws POINotFoundException {
        POI poiFromDB = get(id);
        poiRepository.delete(poiFromDB);
        return poiFromDB;
    }
}
