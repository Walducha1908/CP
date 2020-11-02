package cp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Trace {
    public String userId;
    public POI visitedPOI;
    public LocalDateTime timeOfEntry;
    public LocalDateTime timeOfExit;

}
