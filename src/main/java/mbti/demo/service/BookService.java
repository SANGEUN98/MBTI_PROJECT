package mbti.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.Book;
import mbti.demo.domain.BoxMovie;
import mbti.demo.domain.Movie;
import mbti.demo.mybatis.BookMapper;
import mbti.demo.repository.BookRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService implements BookServiceInterface {


    private final BookRepository bookInterface;
    private final BookMapper bookMapper;



    @Override
    public Page<Book> findByGenre(Pageable pageable,String genre) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Book> bookList = bookMapper.findByGenre(rowBounds,genre);
        List<Book> bookSize = bookMapper.findByGenre(genre);
        
        int pageSize = bookSize.size();


        return new PageImpl<>(bookList, pageable, pageSize);
    }

    @Override
    public Book save(Book book) {
        return bookInterface.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookInterface.findAll();
    }

    @Override
    public Page<Book> findByAnalystType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Book> bookList = bookMapper.findByAnalystType(rowBounds);
        List<Book> bookSize= bookMapper.findByAnalystType();
        int pageSize = bookSize.size();

        return new PageImpl<>(bookList, pageable, pageSize);
    }

    @Override
    public Page<Book> findByDiplomaticType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Book> bookList = bookMapper.findByDiplomaticType(rowBounds);
        List<Book> bookSize= bookMapper.findByDiplomaticType();

        int pageSize = bookSize.size();
        return new PageImpl<>(bookList, pageable, pageSize);
    }

    @Override
    public Page<Book> findByAdministratorType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Book> bookList = bookMapper.findByAdministratorType(rowBounds);
        List<Book> bookSize= bookMapper.findByAdministratorType();
        int pageSize = bookSize.size();

        return new PageImpl<>(bookList, pageable, pageSize);
    }

    @Override
    public Page<Book> findByExplorerType(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Book> bookList = bookMapper.findByExplorerType(rowBounds);
        List<Book> bookSize= bookMapper.findByExplorerType();
        int pageSize = bookSize.size();

        return new PageImpl<>(bookList, pageable, pageSize);
    }

    public Page<Book> findBookWithPaging(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Book> bookList = bookMapper.findBookWithPaging(rowBounds);
        long totalCount = bookMapper.countTotalBooks();

        return new PageImpl<>(bookList, pageable, totalCount);
    }
}
