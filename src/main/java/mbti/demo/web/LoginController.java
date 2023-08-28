package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mbti.demo.domain.Member;
import mbti.demo.repository.SessionConst;
import mbti.demo.service.LoginInterface;
import mbti.demo.service.MemberServiceInterface;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberServiceInterface memberServiceInterface;
    private final LoginInterface loginInterface;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginInterface.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.rejectValue("password", "fail login", "아이디 또는 비밀번호가 바르지 않습니다.");
            return "login/login";
        }

        Optional<Member> member = memberServiceInterface.findById(loginMember.getId());
        System.out.println(member.get().getNickName()); 

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        session.setAttribute("member", member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/find_id")
    public String find_LoginId_Form(@ModelAttribute("member") findLoginIdForm form) {
        return "login/find_LoginId";
    }

    @PostMapping("/find_id")
    public String find_Loginid(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
        Member result = memberServiceInterface.findByNameEmail(name, email).get();
        model.addAttribute("member", result);
        return "login/find_LoginId_result";

    }

    @GetMapping("/find_pw")
    public String find_PassWord_Form(@ModelAttribute("member") findPasswordForm form) {
        return "login/find_pw";
    }

    @PostMapping("/find_pw")
    public String find_PassWord(@RequestParam("loginId") String loginId, @RequestParam("name") String name, @RequestParam("email") String email, Model model) {
        Member result = memberServiceInterface.findPasswordNameEmail(loginId, name, email).get();
        model.addAttribute("member", result);
        return "login/find_pw_result";
    }


}
