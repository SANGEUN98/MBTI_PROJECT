package mbti.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.BoxRank;
import mbti.demo.domain.Movie;
import mbti.demo.mybatis.MovieMapper;
import mbti.demo.repository.MovieRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService implements MovieServiceInterface {


    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public Page<Movie> findByGenre(Pageable pageable,String genre) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findByGenre(rowBounds,genre);
        List<Movie> movieSize = movieMapper.findByGenre(genre);
        
        int fageSize = movieSize.size();


        return new PageImpl<>(movieList, pageable, fageSize);
    }

    // 일반적
//    @Override
//    public List<Movie> findByAdministratorType() {
//        return movieRepository.findByAdministratorType();
//    }
//
//    @Override
//    public List<Movie> findByExplorerType() {
//        return movieRepository.findByExplorerType();
//    }
//
//    @Override
//    public List<Movie> findByDiplomaticType() {
//        return movieRepository.findByDiplomaticType();
//    }
//
//    @Override
//    public List<Movie> findByAnalystType() {
//        return movieRepository.findByAnalystType();
//    }
//
//    @Override
//    public List<Movie> findAll() {
//        return movieRepository.findAll();
//    }

    // 페이징용
    @Override
    public Page<Movie> findByAdministratorType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findByAdministratorType(rowBounds);
        long totalCount = movieMapper.countTotalMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }

    @Override
    public Page<Movie> findByExplorerType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findByExplorerType(rowBounds);
        long totalCount = movieMapper.countTotalMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }

    @Override
    public Page<Movie> findByDiplomaticType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findByDiplomaticType(rowBounds);
        long totalCount = movieMapper.countTotalMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }

    @Override
    public Page<Movie> findByAnalystType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findByAnalystType(rowBounds);
        long totalCount = movieMapper.countTotalMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }

    // 박스오피스
    @Override
    public Page<BoxMovie> findByDailyBox(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<BoxMovie> movieList = movieMapper.findByDailyBox(rowBounds);
        long totalCount = movieMapper.countTotalBoxMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }

    @Override
    public Page<BoxMovie> findBoxMoviesWithPaging(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<BoxMovie> boxMovieList = movieMapper.findBoxMoviesWithPaging(rowBounds);
        long totalCount = movieMapper.countTotalBoxMovies();

        return new PageImpl<>(boxMovieList, pageable, totalCount);
    }


    // 페이징
    @Override
    public Page<Movie> findMoviesWithPaging(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Movie> movieList = movieMapper.findMoviesWithPaging(rowBounds);
        long totalCount = movieMapper.countTotalMovies();

        return new PageImpl<>(movieList, pageable, totalCount);
    }


    // 랭크용
    @Override
    public List<BoxRank> firstRank() {
        return movieRepository.firstRank();
    }

    @Override
    public List<BoxRank> secondRank() {
        return movieRepository.secondRank();
    }

    @Override
    public List<BoxRank> thirdRank() {
        return movieRepository.thirdRank();
    }

    @Override
    public List<BoxRank> fourthRank() {
        return movieRepository.fourthRank();
    }

    @Override
    public List<BoxRank> fifthRank() {
        return movieRepository.fifthRank();
    }

    @Override
    public List<BoxRank> sixthRank() {
        return movieRepository.sixthRank();
    }

    @Override
    public List<BoxRank> seventhRank() {
        return movieRepository.seventhRank();
    }

    @Override
    public List<BoxRank> eighthRank() {
        return movieRepository.eighthRank();
    }

    @Override
    public List<BoxRank> ninthRank() {
        return movieRepository.ninthRank();
    }

    @Override
    public List<BoxRank> tenthRank() {
        return movieRepository.tenthRank();
    }
}
