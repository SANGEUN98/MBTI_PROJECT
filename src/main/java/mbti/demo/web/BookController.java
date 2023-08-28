package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BestBook;
import mbti.demo.domain.Book;
import mbti.demo.domain.Movie;
import mbti.demo.domain.Recommend;
import mbti.demo.repository.BestBookRepository;
import mbti.demo.service.BestBookService;
import mbti.demo.service.BestBookServiceInterface;
import mbti.demo.service.BookServiceInterface;
import mbti.demo.service.RecommendService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/book")
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookServiceInterface bookServiceInterface;
    private final BestBookServiceInterface bestbookServiceInterface;
    @Autowired
    private RecommendService recommendService;


    // 베스트 샐러
    @GetMapping("/best")
    public String findAll(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }

        List<BestBook> bestBook = bestbookServiceInterface.findAll();

        model.addAttribute("login", login);
        model.addAttribute("bestBook",bestBook);
        return "book/best";
    }

    @GetMapping("/mbti/type/{id}")
    public String getMbtiBookGenre(@PathVariable("id") String id, @PageableDefault(size = 10, sort = "rank") Pageable pageable, Model model,HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }else {
            login="header_login.html";
        }

        if(id.equals("NT")){ // 분석가형
            String[] mbtiList = {"INTJ","INTP","ENTJ","ENTP"};
            Page<Book> book = bookServiceInterface.findByAnalystType(pageable);
            int nowPage = book.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, book.getTotalPages());
            String type = "분석가형(_NT_)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiList);
            model.addAttribute("book", book);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "NT");
            return "/book/bookMbti";
        }else if(id.equals("NF")){ // 외교관형
            String[] mbtiLsit = {"INFP","INFJ","ENFJ","ENFP"};
            Page<Book> book = bookServiceInterface.findByDiplomaticType(pageable);
            int nowPage = book.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, book.getTotalPages());
            String type = "외교관형(_NF_)";
            model.addAttribute("type", type);
            model.addAttribute("book", book);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("mbti", "NF");
            return "/book/bookMbti";
        }else if(id.equals("SJ")){ // 관리자형
            String[] mbtiLsit = {"ISTJ","ISFJ","ESTJ","ESFJ"};
            Page<Book> book = bookServiceInterface.findByAdministratorType(pageable);
            int nowPage = book.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, book.getTotalPages());
            String type = "관리자형(_S_J)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("book", book);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "SJ");
            return "/book/bookMbti";
        }else if(id.equals("SP")){ // 탐험가형
            String[] mbtiLsit = {"ISTP","ISFP","ESTP","ESTP"};
            Page<Book> book = bookServiceInterface.findByExplorerType(pageable);
            int nowPage = book.getPageable().getPageNumber() + 1;

            int startPage = Math.max(nowPage -4, 1);
            int endPage = Math.min(nowPage + 5, book.getTotalPages());
            String type = "탐험가형(_S_P)";
            model.addAttribute("type", type);
            model.addAttribute("mbtiList", mbtiLsit);
            model.addAttribute("book", book);
            model.addAttribute("login", login);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("mbti", "SP");
            return "/book/bookMbti";
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

        String bookGenre = recommendService.bookGenre(id);
        Page<Book> book = bookServiceInterface.findByGenre(pageable, bookGenre);

        int nowPage = book.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, book.getTotalPages());

        model.addAttribute("login", login);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("book", book);
        model.addAttribute("mbti", id);
        return "/book/bookMbti";
    }






    @GetMapping("/AnalystType")
    public String findByAnalystType(@PageableDefault(size=10, sort = "title") Pageable pageable, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }
        model.addAttribute("login", login);
        Page<Book> bookPage = bookServiceInterface.findByAnalystType(pageable);
        model.addAttribute("bookPage",bookPage);
        return "book/book1";
    }

    @GetMapping("/DiplomaticType")
    public String findByDiplomaticType(@PageableDefault(size=10, sort = "title") Pageable pageable, Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }
        model.addAttribute("login", login);
        Page<Book> bookPage = bookServiceInterface.findByDiplomaticType(pageable);
        model.addAttribute("bookPage",bookPage);
        return "book/book2";
    }

    @GetMapping("/AdministratorType")
    public String findByAdministratorType(@PageableDefault(size=10, sort = "title") Pageable pageable, Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }
        model.addAttribute("login", login);
        Page<Book> bookPage = bookServiceInterface.findByAdministratorType(pageable);
        model.addAttribute("bookPage",bookPage);
        return "book/book3";
    }

    @GetMapping("/ExplorerType")
    public String findByExplorerType(@PageableDefault(size=10, sort = "title") Pageable pageable, Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String login = "";
        if(session == null){
            login = "header.html";
        }

        else {
            login="header_login.html";
        }
        model.addAttribute("login", login);
        Page<Book> bookPage = bookServiceInterface.findByExplorerType(pageable);
        model.addAttribute("bookPage",bookPage);
        return "book/book4";
    }

}

