package mbti.demo.repository;

import mbti.demo.domain.UserBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookInfoRepository extends JpaRepository<UserBookInfo, Long> {
}