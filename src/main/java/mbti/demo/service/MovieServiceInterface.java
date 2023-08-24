package mbti.demo.service;

import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieServiceInterface {
    Page<Movie> findByGenre(Pageable pageable, String genre);
    // 일반
//    List<Movie> findByAnalystType();
    // 페이징용
    Page<Movie> findByAnalystType(Pageable pageable);

    // 일반
//    List<Movie> findByDiplomaticType();
    // 페이징용
    Page<Movie> findByDiplomaticType(Pageable pageable);

    // 일반
//    List<Movie> findByAdministratorType();
    // 페이징용
    Page<Movie> findByAdministratorType(Pageable pageable);

    // 일반
//    List<Movie> findByExplorerType();
    // 페이징용
    Page<Movie> findByExplorerType(Pageable pageable);

    // 박스오피스
    Page<BoxMovie> findByDailyBox(Pageable pageable);
    Page<BoxMovie> findBoxMoviesWithPaging(Pageable pageable);

    // 일반
//    List<Movie> findAll();
    // 페이징용
    Page<Movie> findMoviesWithPaging(Pageable pageable);

    // 랭크용
    List<BoxRank> firstRank();
    List<BoxRank> secondRank();
    List<BoxRank> thirdRank();
    List<BoxRank> fourthRank();
    List<BoxRank> fifthRank();
    List<BoxRank> sixthRank();
    List<BoxRank> seventhRank();
    List<BoxRank> eighthRank();
    List<BoxRank> ninthRank();
    List<BoxRank> tenthRank();
}


