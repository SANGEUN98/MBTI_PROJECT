package mbti.demo.mybatis;

import lombok.RequiredArgsConstructor;
import mbti.demo.domain.Book;
import mbti.demo.domain.BoxMovie;
import mbti.demo.repository.BookRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisBookRepository implements BookRepository {
    private final BookMapper bookMemberMapper;


    public List<Book> findByGenre(int offset, int limit, String genre){
        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findByGenre(rowBounds, genre);
    };


    @Override
    public Book save(Book book) {
        bookMemberMapper.save(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return bookMemberMapper.findAll();
    }

    @Override
    public List<Book> findByAnalystType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findByAnalystType(rowBounds);
    }

    @Override
    public List<Book> findByDiplomaticType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findByDiplomaticType(rowBounds);
    }

    @Override
    public List<Book> findByAdministratorType(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findByAdministratorType(rowBounds);
    }

    @Override
    public List<Book> findByExplorerType(int offset, int limit) {

        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findByExplorerType(rowBounds);
    }

    @Override
    public List<Book> findBookWithPading(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return bookMemberMapper.findBookWithPaging(rowBounds);
    }

    @Override
    public long countTotalBooks() {
        return bookMemberMapper.countTotalBooks();
    }

}
