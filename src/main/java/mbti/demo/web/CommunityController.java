package mbti.demo.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mbti.demo.domain.Community;
import mbti.demo.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    @Autowired
    private final CommunityRepository communityRepository;

    @GetMapping("/community")
    public String communityList(Model model){
        List<Community> list = communityRepository.findAll();
        model.addAttribute("list", list);
        return "community/index_login";
    }
    @PostMapping("/community/{memberId}")
    public String community(@RequestParam(required = false, defaultValue = "created",value = "orderby") @PathVariable("memberId")String memberId, Community community, Model model){
        System.out.println(memberId);
//        community.setMemberId(id);
        System.out.println(community.getMemberId());
        communityRepository.save(community);
        List<Community> list = communityRepository.findAll();
        model.addAttribute("list", list);
        return "community/index_login";
    }


    @GetMapping("/community/update/{id}")
    public String edit(@PathVariable("id")Long id, Model model) {
        communityRepository.getReferenceById(id);
        Community list = new Community();
        list.setContent(list.getContent());
        model.addAttribute("list", list);
        return "community/index_update";
    }
    @PutMapping("/community/update/{id}")
    public String update(@Valid Community list, BindingResult result){
        if(result.hasErrors()){
            return "community/index_update";
        }
        communityRepository.save(list);
        return "redirect: /";
    }


    @GetMapping("/community/delete")
    public String test(Long id, Model model) {
        System.out.println(id);
        communityRepository.deleteById(id);
    //    replyRepository.save(reply);
        List<Community> list = communityRepository.findAll();
        model.addAttribute("list", list);
        return "community/index_login";
    }

}
