package mbti.demo.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import mbti.demo.repository.MovieRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisMovieRepository implements MovieRepository {
    private final MovieMapper movieMapper;


    @Override
    public List<Movie> findByGenre(int offset, int limit,String genre) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByGenre(rowBounds,genre);
    }

    // 일반적
//    @Override
//    public List<Movie> findByAnalystType() {
//        return movieMapper.findByAnalystType();
//    }
//
//    @Override
//    public List<Movie> findByDiplomaticType() {
//        return movieMapper.findByDiplomaticType();
//    }
//
//    @Override
//    public List<Movie> findByExplorerType() {
//        return movieMapper.findByExplorerType();
//    }
//
//    @Override
//    public List<Movie> findByAdministratorType() {
//        return movieMapper.findByAdministratorType();
//    }
//
//    @Override
//    public List<Movie> findAll() {
//        return movieMapper.findAll();
//    }

    // 페이징용
    @Override
    public List<Movie> findByAnalystType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByAnalystType(rowBounds);
    }

    @Override
    public List<Movie> findByDiplomaticType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByDiplomaticType(rowBounds);
    }

    @Override
    public List<Movie> findByExplorerType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByExplorerType(rowBounds);
    }

    @Override
    public List<Movie> findByAdministratorType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByAdministratorType(rowBounds);
    }

    @Override
    public List<Movie> findMoviesWithPaging(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findMoviesWithPaging(rowBounds);
    }

    // 박스오피스
    @Override
    public List<BoxMovie> findByDailyBox(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findByDailyBox(rowBounds);
    }

    @Override
    public List<BoxMovie> findBoxMoviesWithPaging(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return movieMapper.findBoxMoviesWithPaging(rowBounds);
    }

    @Override
    public long countTotalBoxMovies() {
        return movieMapper.countTotalBoxMovies();
    }


    @Override
    public long countTotalMovies() {
        return movieMapper.countTotalMovies();
    }


    // 랭크용
    @Override
    public List<BoxRank> firstRank() {
        return movieMapper.firstRank();
    }

    @Override
    public List<BoxRank> secondRank() {
        return movieMapper.secondRank();
    }

    @Override
    public List<BoxRank> thirdRank() {
        return movieMapper.thirdRank();
    }

    @Override
    public List<BoxRank> fourthRank() {
        return movieMapper.fourthRank();
    }

    @Override
    public List<BoxRank> fifthRank() {
        return movieMapper.fifthRank();
    }

    @Override
    public List<BoxRank> sixthRank() {
        return movieMapper.sixthRank();
    }

    @Override
    public List<BoxRank> seventhRank() {
        return movieMapper.seventhRank();
    }

    @Override
    public List<BoxRank> eighthRank() {
        return movieMapper.eighthRank();
    }

    @Override
    public List<BoxRank> ninthRank() {
        return movieMapper.ninthRank();
    }

    @Override
    public List<BoxRank> tenthRank() {
        return movieMapper.tenthRank();
    }
}
