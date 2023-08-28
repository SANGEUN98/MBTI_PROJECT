package mbti.demo.config;

import lombok.RequiredArgsConstructor;
import mbti.demo.mybatis.*;
import mbti.demo.repository.BestBookRepository;
import mbti.demo.repository.BookRepository;
import mbti.demo.repository.MovieRepository;
import mbti.demo.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MovieMapper movieMapper;
    private final BookMapper bookMapper;
    private final BestBookMapper bestBookMapper;


    @Bean
    public MovieServiceInterface movieServiceInterface(){
        return new MovieService(movieRepository(), movieMapper);

    }
    @Bean
    public MovieRepository movieRepository() {
        return new MyBatisMovieRepository(movieMapper);
    }
    @Bean
    public BookServiceInterface bookServiceInterface() {
        return new BookService(bookInterface(), bookMapper);
    }
    @Bean
    public BookRepository bookInterface() {
        return new MyBatisBookRepository(bookMapper);
    }

    @Bean
    public BestBookServiceInterface bestbookServiceInterface() {
        return new BestBookService(bestbookInterface());
    }
    @Bean
    public BestBookRepository bestbookInterface() {
        return new MyBatisBestBookRepository(bestBookMapper);
    }
}


