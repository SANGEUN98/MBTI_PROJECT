package mbti.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    private String text;
    @Column(name = "login_id")
    private String loginId;
}
