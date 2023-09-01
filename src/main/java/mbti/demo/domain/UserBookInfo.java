package mbti.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookdata")
public class UserBookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // String 타입으로 변경
    @Column(name = "login_id")
    private String loginId;

    private String description;

    private String title;

    private String author;

}
