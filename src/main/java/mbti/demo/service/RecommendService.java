package mbti.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.val;
import mbti.demo.domain.Recommend;
import mbti.demo.repository.RecommendRepository;
import oracle.security.o3logon.a;


@Service
@RequiredArgsConstructor
public class RecommendService{

    final RecommendRepository recommendRepository;

    // 임의로 mbti 추천 장르 셋팅
    public void setting() {
        System.out.println(recommendRepository);
        String[] mbtiList = {"INFP","INFJ","INTP","INTJ","ISFP","ISFJ","ISTP","ISTJ","ENFP","ENFJ","ENTP","ENTJ","ESFP","ESFJ","ESTP","ESTJ"};
        String[] movieGenreList = {"로맨스","드라마","SF","미스터리","멜로","가족","범죄","전쟁","판타지","공연","어드벤처","스릴러","애니메이션","사극","뮤지컬","기타"};
        
        for (int i = 0; i < mbtiList.length; i++){
            Recommend recommend = new Recommend();
            String mbti = mbtiList[i];
            String movie = movieGenreList[i];
            System.out.println(mbti);
            System.out.println(movie);
            recommend.setMbti(mbti);
            recommend.setMovieGenre(movie);
            System.out.println("세이브전");
            recommendRepository.save(recommend);
            System.out.println("dfdfdsfa");
        };
    }
    

    // 해당 mbti의 추천 책 장르를 가져옴
    public String bookGenre(String mbti) {
        List<Recommend> book = recommendRepository.findByMbti(mbti);
        String bookGenre = book.get(0).getBookGenre();
        return bookGenre;
    }
    
    // 해당 mbti의 추천 영화 장르를 가져옴
    public String movieGenre(String mbti) {
        List<Recommend> movies = recommendRepository.findByMbti(mbti);
        String movieGenre = movies.get(0).getMovieGenre();
        return movieGenre;
    }
}

//HTML

// 글 작성 폼에 들어갈 것
// enctype="multipart/form-data" 

// 파일 추가 
// <input type="file" name="file">


// 글 작성 컨트롤러 
// @PostMapping("/board/writepro")
//    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{
//     boardService.write(board, file);
//     return "message";
//    }


// 서비스
// 글 작성 처리
    // public void write(Board board, MultipartFile file) throws Exception{ // MultipartFile은 HTTP 요청으로부터 업로드된 파일을 다루는 인터페이스

    //     String projectPath = System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\static\\files";

    //     UUID uuid = UUID.randomUUID();

    //     String fileName = uuid + "_" + file.getOriginalFilename(); // 해당 파일의 원본이름을 반환

    //     File saveFile = new File(projectPath, fileName);

    //     file.transferTo(saveFile);

    //     board.setFilename(fileName);
    //     board.setFilepath("/files/" + fileName);

    //     boardRepository.save(board);
    // }


// 테이블 추가

//  private String filename;

// private String filepath;






