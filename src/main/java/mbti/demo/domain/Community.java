package mbti.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@SequenceGenerator(name = "seq_community", sequenceName = "community_seq", allocationSize = 1)
public class Community extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_community")
    private Long id;
    @Column(name = "login_id")
    private String login_id;
    private String memberId;

    private String content;

    @ColumnDefault("0")
    private Integer viewCount;
    private Integer likeCount;

    public Community(){
    };
};
