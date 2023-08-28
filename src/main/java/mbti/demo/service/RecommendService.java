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
//import oracle.security.o3logon.a;


@Service
@RequiredArgsConstructor
public class RecommendService{

    final RecommendRepository recommendRepository;

    // 임의로 mbti 추천 장르 셋팅
    public void setting() {
        String[] mbtiList = {"INFP","INFJ","INTP","INTJ","ISFP","ISFJ","ISTP","ISTJ","ENFP","ENFJ","ENTP","ENTJ","ESFP","ESFJ","ESTP","ESTJ"};
        String[] movieGenreList = {"로맨스","드라마","SF","미스터리","멜로","가족","범죄","전쟁","판타지","공연","어드벤처","스릴러","애니메이션","사극","뮤지컬","기타"};
        String[] bookGenreList = {"소설","교양","SF","과학","어린이","심리학","경영","금융","판타지","에세이","정치","협상","여행","청소년","마케팅","물리학"};
        String[] musicGenreList = {"드라마","애니","랩","Soul","POP","인디","힙합","트로트","댄스","POP","Soul","포크","댄스","POP","랩","인디"};

        for (int i = 0; i < mbtiList.length; i++){
            Recommend recommend = new Recommend();
            String mbti = mbtiList[i];
            String movie = movieGenreList[i];
            String book = bookGenreList[i];
            String music = musicGenreList[i];

            recommend.setMbti(mbti);
            recommend.setMovieGenre(movie);
            recommend.setBookGenre(book);
            recommend.setMusicGenre(music);
            recommendRepository.save(recommend);
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

    public String musicGenre(String mbti) {
        List<Recommend> movies = recommendRepository.findByMbti(mbti);
        String musicGenre = movies.get(0).getMusicGenre();
        return musicGenre;
    }
}







