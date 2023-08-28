package mbti.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Entity
@Data
public class Member {
    // 시퀀스 자동 생성용 id
    @Id
    private Long id;
    // 로그인 아이디(공백 불가)
    @NotBlank(message = "아이디는 띄어쓰기가 불가합니다.")
    @NotEmpty(message = "비워두시면 안 됩니다.")
    private String loginId;

    // 비밀번호(공백 불가)
    @NotBlank(message = "비밀번호는 띄어쓰기가 불가합니다.")
    private String password;
    @NotBlank(message = "비밀번호가 일치하지 않습니다.")
    private String password2;
    // 사용자 이름
    @NotBlank(message = "이름을 입력하세요." )
    private String name;
    // 사용자 이메일
    private String email;
    // 사용지 전화번호
    private String phoneNumber;
    // 사용자 mbti
    private String mbti;
    // 사용자 닉네임
    private String nickName;

    // 기본 생성자
    public Member(){}

    // 필수 항목만 입력
    public Member(String loginId, String password, String password2, String name, String mbti, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.mbti = mbti;
        this.nickName = nickName; 
    }

    // 선택 중 이메일만 입력
    public Member(String loginId, String password, String password2, String name, String email, String mbti, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.email = email;
        this.mbti = mbti;
        this.nickName = nickName;
    }

    // 모두 입력
    public Member(String loginId, String password, String password2, String name, String email, String phoneNumber, String mbti, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mbti = mbti;
        this.nickName = nickName;
    }
}
