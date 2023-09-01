package mbti.demo.domain;
import lombok.Data;

@Data
public class BestBook {
    private Long rank;
    private String  title;
    private String  img;
    private String  author;
    private String price;
    private String description;
    private String link;

    public BestBook(){
    }

}
