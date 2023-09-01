package mbti.demo.web;

import mbti.demo.domain.BookRecommendationResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookRecommendationController {

    @GetMapping("/get_book_recommendations")
    public String displayRecommendations(Model model) {
        String flaskServerUrl = "http://localhost:4000/get_book_recommendations";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<BookRecommendationResponse> responseEntity = restTemplate.exchange(
                flaskServerUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookRecommendationResponse>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            BookRecommendationResponse response = responseEntity.getBody();
            model.addAttribute("recommend", response.getRecommendations());
            System.out.println(response.getRecommendations());
            return "recommendation";
        } else {
            model.addAttribute("error", "No recommendations available.");
            return "error";
        }
    }
}
