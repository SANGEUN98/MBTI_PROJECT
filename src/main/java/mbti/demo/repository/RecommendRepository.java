package mbti.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.demo.domain.Recommend;

public interface RecommendRepository extends JpaRepository<Recommend, Integer>{
    List<Recommend> findByMbti(String mbti);
}
