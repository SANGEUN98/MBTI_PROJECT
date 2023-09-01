package mbti.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Music{

    @Id
    private Long ranking;
    private String img;
    private String title;
    private String singer;
    private String category;
}
