package mbti.demo.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RecommendationResponse {
    private Map<String, List<List<Object>>> recommendations;

    @Data
    public static class Recommendations {
        private String movieName;
        private double similarityScore;
        private String info;
        private String genre;
        private String url;
    }
}

