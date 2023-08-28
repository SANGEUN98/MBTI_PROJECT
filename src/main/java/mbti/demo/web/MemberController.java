package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mbti.demo.domain.Member;
import mbti.demo.repository.MemberUpdateDto;
import mbti.demo.repository.SessionConst;
import mbti.demo.service.MemberServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceInterface memberServiceInterface;

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute
                       Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println(member);

        Member savedMember = memberServiceInterface.save(member);
        
        if (savedMember == null) {
            bindingResult.rejectValue("loginId", "duplicate.loginId",
                    "해당 아이디가 이미 사용중입니다.");
        }
        if(bindingResult.hasErrors()){
            return "member/join";
        }

        redirectAttributes.addAttribute("loginId",savedMember.getLoginId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/";
    }


    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        // 세션에서 로그인한 회원 정보 가져오기
        if(session==null){
            return "main/main_login";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            return "main/main_login";
        }
        model.addAttribute("member",loginMember);
        return "member/mypage";
    }
    @GetMapping("/{loginId}/myInfo")
    public String myInfoForm(@PathVariable String loginId, Model model) {
        Member member = memberServiceInterface.findByLoginId(loginId).get();
        model.addAttribute("member",member);
        return "member/myInfo";
    }

    @PostMapping("/{loginId}/myInfo")
    public String myInfo(@PathVariable String loginId, MemberUpdateDto member) {
        memberServiceInterface.update(loginId, member);

        return "redirect:/mypage";
    }
}
