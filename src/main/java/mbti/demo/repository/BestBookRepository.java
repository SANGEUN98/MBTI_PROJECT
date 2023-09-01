package mbti.demo.repository;
import mbti.demo.domain.BestBook;
import java.util.List;

public interface BestBookRepository {

    BestBook save(BestBook bestbook);
    List<BestBook> findAll();


}
