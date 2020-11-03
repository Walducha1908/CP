package cp.services;

import cp.model.POI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AnalysisService {

    /**
     * Analise traces/POIs
     * By frequent users, lengths of stay etc.
     */
    public void clusterPOIs () throws Exception {
        throw new Exception("Clustering not implemented");
    }

    /**
     * Rank POIs by ??
     */
    public void rankPOIs () throws Exception {
        throw new Exception("Ranking not implemented");
    }

    /**
     * For givenPOI calculate which poi is most often visited
     * @param givenPoi
     * @return most visited POI
     * @throws Exception
     */
    public POI calculateMostVisited(POI givenPoi) throws Exception {
        throw new Exception("Calculation not implemented");
    }
}
