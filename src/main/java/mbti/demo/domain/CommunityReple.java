package mbti.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "seq_reple_generator", sequenceName = "SEQ_REPLE", allocationSize = 1)
public class CommunityReple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reple_generator")
    private Long repleId;

    private Long tableId;

    private String memberId;
    
    private String content;

    private String nickName;

    private String dayTime;
}
