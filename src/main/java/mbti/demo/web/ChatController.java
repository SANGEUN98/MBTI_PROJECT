package mbti.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mbti.demo.domain.Member;

import lombok.RequiredArgsConstructor;
import mbti.demo.domain.ChatRoom;
import mbti.demo.repository.SessionConst;
import mbti.demo.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class ChatController {
    private final ChatService chatService;


    @RequestMapping("/chat/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    @GetMapping("/chat/createRoom")  // GET 요청에 대한 처리 추가
    public String showCreateRoomForm(Model model) {
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "community/index_login";  // 뷰 파일의 경로
    }

    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoom(Model model, @RequestParam String name, String username) {
//        ChatRoom room = chatService.createRoom(name);
//        model.addAttribute("room",room);
//        model.addAttribute("username",username);  //만든사람이 채팅방 1빠로 들어가게 됩니다
        chatService.createRoom(name);
        // Fetch the updated list of chat rooms
//        List<ChatRoom> roomList = chatService.findAllRoom();
//        model.addAttribute("roomList", roomList);
        return "redirect:/community";
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam String roomId, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        ChatRoom room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);   //현재 방에 들어오기위해서 필요한데...... 접속자 수 등등은 실시간으로 보여줘야 돼서 여기서는 못함
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList", roomList);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member",loginMember);
        return "community/open_chatting";
    }

    @PostMapping("/chat/chatRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoomOpen(Model model, @RequestParam String name, String username) {
        chatService.createRoom(name);
        return "redirect:/community/open_chatting";
    }


}
