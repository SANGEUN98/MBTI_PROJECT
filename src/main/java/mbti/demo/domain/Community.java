package mbti.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@SequenceGenerator(name = "seq_community", sequenceName = "community_seq", allocationSize = 1)
public class Community{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_community")
    private Long id;
    private String memberId;
    private String content;
    @ColumnDefault("0")
    private Integer viewCount;
    @ColumnDefault("0")
    private Integer likeCount;

    private String nickName;

    private String dayTime;


    public Community() {
        this.likeCount = 0;
    }
    
}
