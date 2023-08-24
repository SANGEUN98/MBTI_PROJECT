package mbti.demo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import mbti.demo.repository.RecommendRepository;
import mbti.demo.service.MovieService;
import mbti.demo.service.MovieServiceInterface;
import mbti.demo.service.RecommendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceInterface movieServiceInterface;
    private final MovieService movieService;

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private RecommendService recommendService;

    // 테스트용 로그 뽑기
//    @GetMapping("/ii")
//    public String test2(){
//        log.info(movieServiceInterface.findByGenre("액션")+"");
////        log.info(movieServiceInterface.findAll()+"");
//        return "index";
//    }

    // 분석가형
    @GetMapping("/analyst") //@PathVariable long itemId,
    public String AnalystTypeMovie(@PageableDefault(size = 10, sort = "name") Pageable pageable, Model model) {
        Page<Movie> movie = movieServiceInterface.findByAnalystType(pageable);
        String[] mbtiLsit = {"INTJ","INTP","ENTJ","ENTP"};

        int nowPage = movie.getPageable().getPageNumber();
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5, movie.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("movies", movie);
        model.addAttribute("mbtiList", mbtiLsit);
        return "/movie/analyst";
//        return "/movie/movieList";

    }

    // 외교관형
    @GetMapping("/Diplomatic") //@PathVariable long itemId,
    public String DiplomaticTypeMovie(@PageableDefault(size = 10, sort = "name") Pageable pageable, Model model) {
        Page<Movie> movie = movieServiceInterface.findByDiplomaticType(pageable);
        model.addAttribute("movies", movie);
        return "/movie/diplomatic";
//        return "/movie/movieList";

    }

    // 관리자형
    @GetMapping("/administrator") //@PathVariable long itemId,
    public String AdministratorTypeMovie(@PageableDefault(size = 10, sort = "name") Pageable pageable, Model model) {
        Page<Movie> moviePage = movieServiceInterface.findByAdministratorType(pageable);
        model.addAttribute("moviePage", moviePage);
        return "/movie/administrator";
//        return "/movie/movieList";

    }

    // 탐험가형
    @GetMapping("/explorer") //@PathVariable long itemId,
    public String ExplorerTypeMovie(@PageableDefault(size = 10, sort = "name") Pageable pageable, Model model) {
        Page<Movie> movie = movieServiceInterface.findByExplorerType(pageable);
        System.out.println(movie.getSize());
        System.out.println(movie.getTotalElements());
        model.addAttribute("movies", movie);
        return "/movie/explorer";
//        return "/movie/movieList";

    }

    // 박스오피스
//    @GetMapping("/box")
//    public String BoxMovie(@PageableDefault( size = 10, sort = "rank") Pageable pageable, Model model) {
//        Page<BoxMovie> boxMoviePage = movieServiceInterface.findByDailyBox(pageable);
//        model.addAttribute("boxMoviePage", boxMoviePage);
//
//        return "/movie/box_movie";
//    }

    @GetMapping("/box")
    public String BoxRank(@PageableDefault( size = 10, sort = "rank") Pageable pageable,Model model) {
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

    @GetMapping("/list")
//    public String getMovieList(@PageableDefault( size = 10, sort = "rank" , direction = Sort.Direction.DESC) Pageable pageable, Model model) {
    public String getMovieList(Model model) {
//        Page<Movie> moviePage = movieServiceInterface.findMoviesWithPaging(pageable);
//        model.addAttribute("moviePage", moviePage);
//        // 박스오피스 실험용
//        Page<BoxMovie> boxMoviePage = movieServiceInterface.findByDailyBox(pageable);
//        System.out.println(boxMoviePage);
//        model.addAttribute("boxMoviePage", boxMoviePage);
        // 랭킹 실험용
        List<BoxRank> firstRank = movieServiceInterface.firstRank();
        model.addAttribute("firstRank",firstRank);
        return "/movie/movieList";

    }
//    @GetMapping("/post")
//    public Page<Movie> find(Pageable pageable) {
//        Page<Movie> page = movieServiceInterface.findAll(pageable);
//        return page;
//    }

    @GetMapping("/analyst/{id}")
    public String getMbtiMovieGenre(@PathVariable("id") String id, @PageableDefault(size = 10, sort = "rank") Pageable pageable, Model model) {
        
        String movieGenre = recommendService.movieGenre(id);
        Page<Movie> movie = movieServiceInterface.findByGenre(pageable, movieGenre);
        String[] mbtiLsit = {"INTJ","INTP","ENTJ","ENTP"};
        int nowPage = movie.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, movie.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("movies", movie);
        model.addAttribute("mbti", id);
        model.addAttribute("mbtiList", mbtiLsit);
        return "/movie/analyst";
    }

    @GetMapping("/test")
    public String test() {
        recommendService.setting();
        return"";
    }

}