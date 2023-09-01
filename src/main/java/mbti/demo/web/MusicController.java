package mbti.demo.web;

import java.util.Comparator;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mbti.demo.domain.Music;
import mbti.demo.domain.Recommend;
import mbti.demo.repository.MusicRepository;
import mbti.demo.service.RecommendService;

@Controller
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/music/mbti/{id}")
    public String music(@PathVariable("id") String id, Model model,HttpServletRequest request) {
        String[] mbtiList = {"INFP","INFJ","INTP","INTJ","ISFP","ISFJ","ISTP","ISTJ","ENFP","ENFJ","ENTP","ENTJ","ESFP","ESFJ","ESTP","ESTJ"};
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }else {
            login="header_login.html";
        }
        model.addAttribute("login", login);
        model.addAttribute("mbtiList", mbtiList);

        if(id.equals("type")){
            List<Music> top100 = musicRepository.findAllByOrderByRankingAsc();
            model.addAttribute("list", top100);
            return "/music/music";
        }

        String musicGenre = recommendService.musicGenre(id);
        System.out.println(musicGenre);

        List<Music> lista = musicRepository.findAll();
        System.out.println(lista);
        List<Music> list = musicRepository.findBycategoryContaining(musicGenre);
        System.out.println(list);
        model.addAttribute("list", list);


        return "/music/music";
    }
    
}
