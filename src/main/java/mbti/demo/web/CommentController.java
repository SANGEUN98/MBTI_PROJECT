package mbti.demo.web;

import jakarta.servlet.http.HttpSession;
import mbti.demo.domain.Comment;
import mbti.demo.domain.Member;
import mbti.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private UserController userController;

    @Autowired
    private CommentService commentService;
    @GetMapping("/comments")
    public List<Comment> getComments() {
        List<Comment> comments = commentService.getAllComments();
        return comments;
    }
    @PostMapping("/comments")
    public Comment addComment(@RequestBody Map<String, String> requestBody, HttpSession session) {
        // 현재 로그인된 사용자의 정보 가져오기
        ResponseEntity<Member> responseEntity = userController.getUserInfo(session);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Member loginMember = responseEntity.getBody();
            if (loginMember != null) {
                Comment comment = new Comment();
                comment.setLoginId(loginMember.getLoginId()); // 댓글에 사용자의 loginId 설정
                comment.setText(requestBody.get("comment")); // JSON 데이터에서 comment 필드 값을 가져와서 설정
                return commentService.saveComment(comment);
            }
        }
        return null;
    }
}
