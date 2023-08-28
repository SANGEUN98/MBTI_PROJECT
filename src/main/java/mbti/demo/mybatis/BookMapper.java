package mbti.demo.mybatis;
import mbti.demo.domain.Book;
import mbti.demo.domain.Movie;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> findByGenre(RowBounds rowBounds,String genre);
    List<Book> findByGenre(String string);

    void save(Book book);
    List<Book> findAll();
    List<Book> findByAnalystType(RowBounds rowBounds); //분석가형
    List<Book> findByAnalystType(); //분석가형

    List<Book> findByDiplomaticType(RowBounds rowBounds); //외교관형
    List<Book> findByDiplomaticType(); //외교관형

    List<Book> findByAdministratorType(RowBounds rowBounds); //관리자형
    List<Book> findByAdministratorType(); //관리자형

    List<Book> findByExplorerType(RowBounds rowBounds); //탐험가형
    List<Book> findByExplorerType(); //탐험가형

    List<Book> findBookWithPaging(RowBounds rowBounds);
    long countTotalBooks();
}
