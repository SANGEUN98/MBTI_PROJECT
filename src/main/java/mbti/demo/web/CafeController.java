package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.Cafe;
import mbti.demo.domain.Cinema;
import mbti.demo.domain.Food;
import mbti.demo.service.CafeService;
import mbti.demo.service.CinemaService;
import mbti.demo.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final CinemaService cinemaService;
    private final FoodService foodService;

    @GetMapping("/getAddress")
    public String getAddress(Model model) {
        List<Cafe> addresses = cafeService.getAddress();
        model.addAttribute("addresses", addresses);

        return "kaka"; // Render kaka.html template
    }

    @GetMapping("/getAllCafes")
    public String getAllCafes(Model model) {
        List<Cafe> cafeInfo = cafeService.getAllCafes();
        model.addAttribute("cafeInfo", cafeInfo);
        return "kaka"; // Render kaka.html template
    }
    @GetMapping("/getAllData")
    public String getAllData(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }
        List<Cinema> cinemaInfo = cinemaService.getAllCinemas();
        List<Cafe> cafeInfo = cafeService.getAllCafes();
        List<Food> foodInfo = foodService.getAllFoods();
        model.addAttribute("login", login);
        model.addAttribute("cinemaInfo", cinemaInfo);
        model.addAttribute("cafeInfo", cafeInfo);
        model.addAttribute("foodInfo", foodInfo);
        return "kaka"; // Render kaka.html template
    }
}