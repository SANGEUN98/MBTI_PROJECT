package mbti.demo.mybatis;

import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;

@Mapper

public interface MovieMapper {
    List<Movie> findByGenre(RowBounds rowBounds,String genre);
     List<Movie> findByGenre(String genre);

    // 일반
//    List<Movie> findByAnalystType();
    // 페이징용
    List<Movie> findByAnalystType(RowBounds rowBounds);

    // 일반
//    List<Movie> findByDiplomaticType();
    // 페이징용
    List<Movie> findByDiplomaticType(RowBounds rowBounds);

    // 일반
//    List<Movie> findByAdministratorType();
    // 페이징용
    List<Movie> findByAdministratorType(RowBounds rowBounds);

    // 일반
//    List<Movie> findByExplorerType();
    // 페이징용
    List<Movie> findByExplorerType(RowBounds rowBounds);

    // 일반
//    List<Movie> findAll();
    // 페이징용
    List<Movie> findMoviesWithPaging(RowBounds rowBounds);

    // 박스오피스용
    List<BoxMovie> findByDailyBox(RowBounds rowBounds);
    List<BoxMovie> findBoxMoviesWithPaging(RowBounds rowBounds);
    long countTotalBoxMovies();


    // 페이징을 위한 신규 추가
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
