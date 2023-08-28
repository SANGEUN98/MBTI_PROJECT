package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mbti.demo.domain.ChatRoom;
import mbti.demo.domain.Community;
import mbti.demo.domain.CommunityReple;
import mbti.demo.domain.Member;
import mbti.demo.repository.CommunityRepository;
import mbti.demo.service.ChatService;
import mbti.demo.service.CommunityRepleService;
import mbti.demo.service.MemberServiceInterface;
//import oracle.security.o3logon.a;

import org.apache.http.impl.auth.HttpAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityRepository communityRepository;
    private final MemberServiceInterface memberServiceInterface;
    private final ChatService chatService;

    @Autowired
    private CommunityRepleService communityReplesService;

//  LocalDate nowDay = LocalDate.now();
//         LocalTime nowTime = LocalTime.now();
//         DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//         DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");

//         String day = nowDay.format(dayFormatter);
//         String time = nowTime.format(timeFormatter);

//         String dayTime = day+"-"+time;

//         String[]parts = dayTime.split("-");
//         System.out.println(parts[1]);


//         Long year = Long.valueOf(parts[0]);   
//         Long month = Long.valueOf(parts[1]);
//         Long day1 = Long.valueOf(parts[2]);
//         Long hour = Long.valueOf(parts[3]);
//         Long min = Long.valueOf(parts[4]);




    // 커뮤니티 게시물 목록 조회
    @GetMapping("/community")
    public String communityList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
        Page<Community> list = communityRepository.findAll(pageable);
        List<Member> members = memberServiceInterface.findAll();
        List<ChatRoom> roomList = chatService.findAllRoom();
        List<CommunityReple> reple = communityReplesService.repleList();

        System.out.println(reple);

        model.addAttribute("reple", reple);
        model.addAttribute("roomList", roomList);
        model.addAttribute("list", list);
        model.addAttribute("members", members);
        return "community/index_login";
    }

    //게시물 생성
    @PostMapping("/community/{memberId}")
    public String community(@PathVariable("memberId") String memberId, Community community, Model model) {
        LocalDate nowDays = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");

        String formatDay = nowDays.format(dayFormatter);
        String formatTime = nowTime.format(timeFormatter);
        String dayTime = formatDay+ "-" + formatTime;
        
        community.setDayTime(dayTime);
        community.setNickName(memberServiceInterface.findById(Long.valueOf(memberId)).get().getNickName());

        System.out.println(memberId);
        System.out.println(community);

        communityRepository.save(community);



        return "redirect:/community";
    }

    //게시물 삭제
    @GetMapping("/community/delete")
    public String delete(Long id, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("loginMember");
        Long memberId = member.getId();
        Long tableMemberId = Long.valueOf(communityRepository.findById(id).get().getMemberId());
        if (memberId != tableMemberId) {
            model.addAttribute("message", "계정이 일치하지 않습니다.");
            model.addAttribute("searchUrl", "/community");
            return "/community/message";
        }
        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/community");
        communityRepository.deleteById(id);

        return "/community/message";
    }

    //게시물 수정
    @GetMapping("/community/update/{id}")
    public String edit(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        Community community = communityRepository.findById(id).orElse(null);
        if (community == null) {
            // 해당 ID의 게시글이 없는 경우 처리
            // 에러 페이지로 리다이렉트하거나 다른 처리를 수행 가능
            return "community/not_found"; //  에러 페이지 뷰 이름 지금은 없음 임의
        }
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("loginMember");
        Long memberId = member.getId();
        Long tableMemberId = Long.valueOf(communityRepository.findById(id).get().getMemberId());
        if (memberId != tableMemberId) {
            model.addAttribute("message", "계정이 일치하지 않습니다.");
            model.addAttribute("searchUrl", "/community");
            return "/community/message";
        }

        model.addAttribute("list", community);
        return "community/index_update";
    }


    @PostMapping("/community/update/{id}")        // @ModelAttribute("list") Community updatedCommunity 폼 데이터를 updatedCommunity 객체에 바인딩
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute("list") Community updatedCommunity, MultipartFile file, Model model) throws Exception {
        //기존 글
        // communityRepository 사용 해당 ID의 게시글을 조회 후 결과를 existingCommunity 여기에 저장
        Community existingCommunity = communityRepository.findById(id).orElse(null);
        if (existingCommunity == null) {
            return "community/not_found";  // 위 코드는 id가 없다면 null 에러 페이지 반환 지금은 없음
        }
        // communityRepository.save(existingCommunity); 여기서 업데이트 된 내용을 다시 db에 저장
        existingCommunity.setContent(updatedCommunity.getContent());

        communityRepository.save(existingCommunity);

        List<Member> members = memberServiceInterface.findAll();

        model.addAttribute("members", members);
        model.addAttribute("message", "글이 수정 되었습니다.");
        model.addAttribute("searchUrl", "/community");
        return "/community/message";
    }



    
    @PostMapping("/community/like/{id}")
    @ResponseBody
    public Map<String, Object> likeCommunity(@PathVariable("id") Long id) {
        Community community = communityRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();

        if (community != null) {
            int newLikeCount = community.getLikeCount() + 1;
            community.setLikeCount(newLikeCount);
            communityRepository.save(community);

            response.put("success", true);
            response.put("newLikeCount", newLikeCount);
        } else {
            response.put("success", false);
        }

        return response;
    }

    @PostMapping("/community/reple")
    public String reple(CommunityReple communityReple, Model model) {
        System.out.println(communityReple);
        communityReplesService.write(communityReple);


        System.out.println(communityReple);
        return "redirect:/community";
    }
}
