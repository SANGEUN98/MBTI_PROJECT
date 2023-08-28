package mbti.demo.service;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.BestBook;
import mbti.demo.repository.BestBookRepository;
import mbti.demo.mybatis.BestBookMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BestBookService implements BestBookServiceInterface{
    private final BestBookRepository bestbookInterface;



    @Override
    public BestBook save(BestBook bestbook) {
        return bestbookInterface.save(bestbook);
    }

    @Override
    public List<BestBook> findAll() {
        return bestbookInterface.findAll();
    }


}
