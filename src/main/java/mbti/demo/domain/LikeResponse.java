package mbti.demo.domain;

public class LikeResponse {
    private Integer likeCount;

    public LikeResponse(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }
}