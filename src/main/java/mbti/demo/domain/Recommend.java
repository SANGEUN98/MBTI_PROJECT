package mbti.demo.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name="seq_recommend_generator", sequenceName = "SEQ_RECOMMEND", allocationSize = 1)
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recommend_generator")
    private Integer id;

    private String mbti;

    private String movieGenre;
    
    private String bookGenre;

}
