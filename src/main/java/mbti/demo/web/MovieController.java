package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import mbti.demo.service.MovieServiceInterface;
import mbti.demo.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceInterface movieServiceInterface;
    @Autowired
    private RecommendService recommendService;

    // 박스오피스 TOP10
    @GetMapping("/box")
    public String BoxRank(@PageableDefault( size = 10, sort = "rank") Pageable pageable,Model model,  HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }

        model.addAttribute("login", login);
        Page<BoxMovie> boxMoviePage = movieServiceInterface.findByDailyBox(pageable);
        model.addAttribute("boxMoviePage", boxMoviePage);
        List<BoxRank> firstRank = movieServiceInterface.firstRank();
        model.addAttribute("firstRank", firstRank);
        List<BoxRank> secondRank = movieServiceInterface.secondRank();
        model.addAttribute("secondRank", secondRank);
        List<BoxRank> thirdRank = movieServiceInterface.thirdRank();
        model.addAttribute("thirdRank", thirdRank);
        List<BoxRank> fourthRank = movieServiceInterface.fourthRank();
        model.addAttribute("fourthRank", fourthRank);
        List<BoxRank> fifthRank = movieServiceInterface.fifthRank();
        model.addAttribute("fifthRank", fifthRank);
        List<BoxRank> sixthRank = movieServiceInterface.sixthRank();
        model.addAttribute("sixthRank", sixthRank);
        List<BoxRank> seventhRank = movieServiceInterface.seventhRank();
        model.addAttribute("seventhRank", seventhRank);
        List<BoxRank> eighthRank = movieServiceInterface.eighthRank();
        model.addAttribute("eighthRank", eighthRank);
        List<BoxRank> ninthRank = movieServiceInterface.ninthRank();
        model.addAttribute("ninthRank", ninthRank);
        List<BoxRank> tenthRank = movieServiceInterface.tenthRank();
        model.addAttribute("tenthRank", tenthRank);

        return "/movie/box_movie";
    }

    // MBTI별 추천 장르
    @GetMapping("/mbti/type/{id}")
    public String getMbtiMovieGenre(@PathVariable("id") String id, @PageableDefault(size = 10, sort = "rank") Pageable pageable, Model model,HttpServletRequest request) {
        System.out.println(id);

        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }else {
            login="header_login.html";
        }



        if(id.equals("NT")){ // 분석가형
            String[] mbtiList = {"INTJ","INTP","ENTJ","ENTP"};
            Page<Movie> movie = movieServiceInterface.findByAnalystType(pageable);
            int nowPage = movie.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, movie.getTotalPages());
            String type = "분석가형(_NT_)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiList);
            model.addAttribute("movies", movie);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "NT");
            return "/movie/movieMbti";
        }else if(id.equals("NF")){ // 외교관형
            String[] mbtiLsit = {"INFP","INFJ","ENFJ","ENFP"};
            Page<Movie> movie = movieServiceInterface.findByDiplomaticType(pageable);
            int nowPage = movie.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, movie.getTotalPages());
            String type = "외교관형(_NF_)";
            model.addAttribute("type", type);
            model.addAttribute("movies", movie);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("mbti", "NF");
            return "/movie/movieMbti";
        }else if(id.equals("SJ")){ // 관리자형
            String[] mbtiLsit = {"ISTJ","ISFJ","ESTJ","ESFJ"};
            Page<Movie> movie = movieServiceInterface.findByAdministratorType(pageable);
            int nowPage = movie.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, movie.getTotalPages());
            String type = "관리자형(_S_J)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("movies", movie);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "SJ");
            return "/movie/movieMbti";
        }else if(id.equals("SP")){ // 탐험가형
            String[] mbtiLsit = {"ISTP","ISFP","ESTP","ESTP"};
            Page<Movie> movie = movieServiceInterface.findByExplorerType(pageable);
            int nowPage = movie.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, movie.getTotalPages());
            String type = "탐험가형(_S_P)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("movies", movie);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "SP");
            return "/movie/movieMbti";
        }else if(id.equals("INTJ") || id.equals("INTP") || id.equals("ENTJ") || id.equals("ENTP")){ // 분석가형
            String[] mbtiList = {"INTJ","INTP","ENTJ","ENTP"};
            String type = "분석가형(_NT_)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiList);
        }else if(id.equals("INFP") || id.equals("INFJ") || id.equals("ENFJ") || id.equals("ENFP")){ // 외교관형
            String[] mbtiLsit = {"INFP","INFJ","ENFJ","ENFP"};
            String type = "외교관형(_NF_)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
        }else if(id.equals("ISTJ") || id.equals("ISFJ") || id.equals("ESTJ") || id.equals("ESFJ")){ // 관리자형
            String[] mbtiLsit = {"ISTJ","ISFJ","ESTJ","ESFJ"};
            String type = "관리자형(_S_J)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
        }else if(id.equals("ISTP") || id.equals("ISFP") || id.equals("ESFP") || id.equals("ESTP")){ // 탐험가형
            String[] mbtiLsit = {"ISTP","ISFP","ESFP","ESTP"};
            String type = "탐험가형(_S_P)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
        }

        String movieGenre = recommendService.movieGenre(id);
        Page<Movie> movie = movieServiceInterface.findByGenre(pageable, movieGenre);
        int nowPage = movie.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, movie.getTotalPages());

        model.addAttribute("login", login);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("movies", movie);
        model.addAttribute("mbti", id);
        return "/movie/movieMbti";
    }
}