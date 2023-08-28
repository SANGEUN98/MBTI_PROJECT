package mbti.demo.mybatis;
import mbti.demo.domain.BestBook;
import mbti.demo.repository.BestBookRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisBestBookRepository implements BestBookRepository {
    private final BestBookMapper bestBookMemberMapper;


    @Override
    public BestBook save(BestBook bestbook) {
        bestBookMemberMapper.save(bestbook);
        return bestbook;
    }

    @Override
    public List<BestBook> findAll() {
        return bestBookMemberMapper.findAll();
    }


}
