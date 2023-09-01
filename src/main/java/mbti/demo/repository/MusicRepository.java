package mbti.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.demo.domain.Music;


@Repository
public interface MusicRepository extends JpaRepository<Music, Long>{
    List<Music> findBycategoryContaining(String category);
    List<Music> findAllByOrderByRankingAsc();
};