package mbti.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mbti.demo.domain.CommunityReple;


@Repository
public interface CommunityRepleRepository extends JpaRepository<CommunityReple, Long>{
    List<CommunityReple> findAllByOrderByRepleIdDesc();
}