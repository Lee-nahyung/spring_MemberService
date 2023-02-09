package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//Ctrl + shift + T 하면 테스트 만들기 쉬움 Jnit5

//@Service

@Transactional //jpa를 통한 데이터 변경은 트랜잭션 안에서 실행되어야 함
public class MemberService {

    /**서비스는 비지니스에 의존적으로 설계,
     * 리포지토리는 개발스럽게 용어들을 선택
     * */
    //이렇게 하지말고
    // private final MemberRepository memberRepository = new MemoryMemberRepository() ;

    private final MemberRepository memberRepository;

    //외부에서 넣어주도록, new로 생성하는 것이 아니라.
   // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**회원 가입
     * */
    public Long join(Member member){
        //비지니스 로직: 같은 이름이 있는 중복회원은 안 된다.

        //로직이 쭉 있는 경우에는 메소드로 뽑는 게 좋음
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /** Optional<Member> result = memberRepository.findByName(member.getName());
     *  result.ifPresent(m ->{
     *             throw new IllegalStateException("이미 존재하는 회원입니다.");
     *         }); 이렇게 옵셔널로 꺼낼 수 있지만 밑 방법도 괜찮
     * */

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
