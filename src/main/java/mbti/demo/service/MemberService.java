package mbti.demo.service;

import lombok.RequiredArgsConstructor;
import mbti.demo.domain.Member;
import mbti.demo.repository.MemberInterface;
import mbti.demo.repository.MemberUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface{
    private final MemberInterface memberInterface;
    
    @Override
    public Member save(Member member) {
        return memberInterface.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberInterface.findById(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberInterface.findByLoginId(loginId);
    }

    @Override
    public List<Member> findAll() {
        return memberInterface.findAll();
    }

    @Override
    public Optional<Member> findByNameEmail(String name, String email) {
        return memberInterface.findByNameEmail(name, email);
    }

    @Override
    public Optional<Member> findByNamePhone(String name, String phone) {
        return memberInterface.findByNamePhone(name, phone);
    }

    @Override
    public Optional<Member> findPasswordNameEmail(String loginId, String name, String email) {
        return memberInterface.findPasswordNameEmail(loginId, name, email);
    }

    @Override
    public Optional<Member> findPasswordNamePhone(String loginId, String name, String email) {
        return memberInterface.findPasswordNamePhone(loginId, name, email);
    }

    @Override
    public void update(String loginId, MemberUpdateDto memberUpdateParam) {
        memberInterface.update(loginId, memberUpdateParam);
    }

    //중복 id check
    public String join(Member member){
        if(validateDuplicateMember(member))
            return null;
        memberInterface.save(member);
        return member.getLoginId();
    }

    private boolean validateDuplicateMember(Member member){
        Optional<Member> result = memberInterface.findByLoginId(member.getLoginId());
        return result.isPresent();
    }
}
