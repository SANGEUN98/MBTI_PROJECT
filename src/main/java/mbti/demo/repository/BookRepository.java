package mbti.demo.repository;
import mbti.demo.domain.Book;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BookRepository {

    
    
    List<Book> findByGenre(int offset, int limit, String genre);
    Book save(Book book);
    List<Book> findAll();
    List<Book> findByAnalystType(int offset, int limit); //분석가형
    List<Book> findByDiplomaticType(int offset, int limit); //외교관형
    List<Book> findByAdministratorType(int offset, int limit); //관리자형
    List<Book> findByExplorerType(int offset, int limit); //탐험가형
    List<Book> findBookWithPading(int offset, int limit);
    long countTotalBooks();
}
