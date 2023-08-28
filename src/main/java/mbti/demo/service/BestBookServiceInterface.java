package mbti.demo.service;

import mbti.demo.domain.BestBook;



import java.util.List;

public interface BestBookServiceInterface {
    BestBook save(BestBook bestbook);

    List<BestBook> findAll();

}
