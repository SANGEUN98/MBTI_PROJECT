package mbti.demo.domain;
import lombok.Data;

@Data
public class Book {
    private String title;
    private String link;
    private String image;
    private String author;
    private Long price;
    private String company;
    private Long releaseDate;
    private Long bookNumber;
    private String description;
    private String genre;


    public Book(){
    }

    @Override
    public String toString() {
        return "book{" +
                "title=" + title +
                ", image='" + image + '\'' +
                ", author='" + author + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
