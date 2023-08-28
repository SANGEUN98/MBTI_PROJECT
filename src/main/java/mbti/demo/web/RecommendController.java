package mbti.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import mbti.demo.domain.Member;
import mbti.demo.domain.Recommend;
import mbti.demo.repository.RecommendRepository;
import mbti.demo.service.MemberService;
import mbti.demo.service.RecommendService;

@Controller
public class RecommendController {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/recommend/test")
    public void test1() {
        Recommend recommend = new Recommend();

        // recommend.setBookGenre("아이고");
        // recommend.setMovieGenre("아이고");
        // recommend.setMusicGenre("아이고");
        // recommend.setMbti("아이고");

        recommendRepository.save(recommend);
    }

    @GetMapping("/recommend")
    public String test(Model model) {
        
        recommendService.setting();
        
        return "recommend";
    }
}