package mbti.demo.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mbti.demo.domain.CommunityReple;
import mbti.demo.repository.CommunityRepleRepository;

@Service
public class CommunityRepleService {

    @Autowired
    private CommunityRepleRepository communityRepleRepository;
    
    // // 글 작성 처리
    // public void write(Board board, MultipartFile file) throws Exception{ // MultipartFile은 HTTP 요청으로부터 업로드된 파일을 다루는 인터페이스

    //     String projectPath = System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\static\\files";

    //     UUID uuid = UUID.randomUUID();

    //     String fileName = uuid + "_" + file.getOriginalFilename(); // 해당 파일의 원본이름을 반환

    //     File saveFile = new File(projectPath, fileName);

    //     file.transferTo(saveFile);

    //     board.setFilename(fileName);
    //     board.setFilepath("/files/" + fileName);

    //     boardRepository.save(board);
    // }
    
    // 게시글 리스트 처리
    public List<CommunityReple> repleList() {

        LocalDate nowDays = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");

        String formatDay = nowDays.format(dayFormatter);
        String formatTime = nowTime.format(timeFormatter);
        String dayTime = formatDay+ "-" + formatTime;

        String[] parts = dayTime.split("-");

        Long nowYear = Long.valueOf(parts[0]);
        Long nowMonth = Long.valueOf(parts[1]);
        Long nowDay = Long.valueOf(parts[2]);
        Long nowHour = Long.valueOf(parts[3]);
        Long nowMin = Long.valueOf(parts[4]);

       
        List<CommunityReple> communityReples = communityRepleRepository.findAllByOrderByRepleIdDesc();

        for (CommunityReple communityReple : communityReples) {

            String[] list = communityReple.getDayTime().split("-");

            Long localYear = Long.valueOf(list[0]);
            Long localMonth = Long.valueOf(list[1]);
            Long localDay = Long.valueOf(list[2]);
            Long localHour = Long.valueOf(list[3]);
            Long localMin = Long.valueOf(list[4]);
            
            Long resultYear = localYear - nowYear;
            Long resultMonth = localMonth - nowMonth;
            Long resultDay = localDay - nowDay;
            Long resultHour = localHour - nowHour;
            Long resultMin = localMin - nowMin;
            Long[] dayList = {resultYear, resultMonth, resultDay, resultHour, resultMin};

            Long a = 0L;
            String b = "";

            for (int i = 0; i < dayList.length; i++) {
                if (dayList[0] > 0L) {
                    a = dayList[0];
                    b = a + "년전";
                    communityReple.setDayTime(b);
                } else if (dayList[1] > 0L) {
                    a = dayList[1];
                    b = a + "개월전";
                    communityReple.setDayTime(b);
                } else if (dayList[2] > 0L) {
                    a = dayList[2];
                    b = a + "일전";
                    communityReple.setDayTime(b);
                } else if (dayList[3] > 0L) {
                    a = dayList[3];
                    b = a + "시간전";
                    communityReple.setDayTime(b);
                } else if (dayList[4] <= 0L) {
                    a = dayList[4];
                    b =  Long.valueOf(Math.abs(a)) + "분전";
                    communityReple.setDayTime(b);
                }

            }

        }

        return communityReples;
    }

    // public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
    //     return boardRepository.findBytitleContaining(searchKeyword, pageable);
    // }

    public void write(CommunityReple communityReple){ // MultipartFile은 HTTP 요청으로부터 업로드된 파일을 다루는 인터페이스

        // String projectPath = System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\static\\files";

        // UUID uuid = UUID.randomUUID();

        // String fileName = uuid + "_" + file.getOriginalFilename(); // 해당 파일의 원본이름을 반환

        // File saveFile = new File(projectPath, fileName);

        // file.transferTo(saveFile);

        // board.setFilename(fileName);
        // board.setFilepath("/files/" + fileName);

        LocalDate nowDay = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");
        String day = nowDay.format(dayFormatter);
        String time = nowTime.format(timeFormatter);
        String dayTime = day+"-"+time;

        communityReple.setDayTime(dayTime);

        communityRepleRepository.save(communityReple);
    }



    // 특정 게시글 불러오기
    public CommunityReple boardView(Long id) {
        return communityRepleRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Long id) {
        communityRepleRepository.deleteById(id);
    }
}