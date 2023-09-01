package mbti.demo.web;

import mbti.demo.domain.RecommendationResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class MovieRecommendationController {


    @GetMapping("/get_recommendations")
    public String displayRecommendations(Model model) {
        String flaskServerUrl = "http://localhost:5000/get_recommendations";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RecommendationResponse> responseEntity = restTemplate.exchange(
                flaskServerUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RecommendationResponse>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            RecommendationResponse response = responseEntity.getBody();
            model.addAttribute("recommendations", response.getRecommendations());
            return "recommendations";
        } else {
            model.addAttribute("error", "No recommendations available.");
            return "error";
        }
    }
}

