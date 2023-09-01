package mbti.demo.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BookRecommendationResponse {
    private Map<String, List<Object>> recommendations;

    @Data
    public static class Recommend {
        private String bookName;
        private String author;
        private String genre;
        private String image;
        private String info;
        private double similarityScore;
        private String title;
    }
}
