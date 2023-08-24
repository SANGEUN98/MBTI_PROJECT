package mbti.demo.repository;

import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> findByGenre(int offset, int limit, String genre);
    // 일반
//    List<Movie> findByAnalystType();
    // 페이징용
    List<Movie> findByAnalystType(int offset, int limit);

    // 일반
//    List<Movie> findByDiplomaticType();
    // 페이징용
    List<Movie> findByDiplomaticType(int offset, int limit);

    // 일반
//    List<Movie> findByAdministratorType();
    // 페이징용
    List<Movie> findByAdministratorType(int offset, int limit);

    // 일반
//    List<Movie> findByExplorerType();
    // 페이징용
    List<Movie> findByExplorerType(int offset, int limit);

    // 일반
//    List<Movie> findAll();
    // 페이징용
    List<Movie> findMoviesWithPaging(int offset, int limit);

    // 박스오피스용
    List<BoxMovie> findByDailyBox(int offset, int limit);
    List<BoxMovie> findBoxMoviesWithPaging(int offset, int limit);
    long countTotalBoxMovies();

    // 전체 영화 수를 가져오는 메서드
    long countTotalMovies();

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
