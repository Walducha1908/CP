package cp.dto;

import cp.model.PlaceType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Builder
public @Data class POIDto {
    public String name;
    public String description;
    public GeoJsonPoint position;
    public PlaceType type;
    public Double studentA;
    public Double studentB;
    public Double teacherA;
    public Double teacherB;
    public Double stuffA;
    public Double stuffB;
}
