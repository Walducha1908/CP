package cp.model.mappers;

import cp.dto.POIDto;
import cp.model.POI;

public class POIMapper {
    public static POIDto mapToDto(POI poi) {
        return POIDto.builder()
                .name(poi.getName())
                .description(poi.getDescription())
                .position(poi.getPosition())
                .type(poi.getType())
                .studentA(poi.getStudentA())
                .studentB(poi.getStudentB())
                .teacherA(poi.getTeacherA())
                .teacherB(poi.getTeacherB())
                .stuffA(poi.getStuffA())
                .stuffB(poi.getStuffB())
                .build();
    }

    public static POI mapFromDto(POIDto poiDto) {
        return POI.builder()
                .name(poiDto.getName())
                .description(poiDto.getDescription())
                .position(poiDto.getPosition())
                .type(poiDto.getType())
                .studentA(poiDto.getStudentA())
                .studentB(poiDto.getStudentB())
                .teacherA(poiDto.getTeacherA())
                .teacherB(poiDto.getTeacherB())
                .stuffA(poiDto.getStuffA())
                .stuffB(poiDto.getStuffB())
                .build();
    }
}
