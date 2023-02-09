package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //리파지토리 하나를 테스트하고 싶은데, 여기서는 new 로 새 객체를 만들었고, java/MemberService에 있는
    //리파지토리와는 다른 애가 됨. 물론 리파지토리에 private static Map<Long, Member> store = new HashMap<>();를 하여 static으로 같은
    //저장소를 공유하게끔 하지만, static을 뺄 경우, 다른 저장소에 저장하게 됨 . 그래서 construct를 만드는 것이 좋음, 이 코드는 java/service/MemberService에 있음
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //이 코드를 통해 MemberService에 같은 리파지토리가 사용이 됨. MemberService 입장에서는 외부에서 넣어짐.
    /** 의존관계 주입
     * */

    //shift+ f11 누르면 이전에 실행한거 그대로 실행해줌

    public
    @AfterEach void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember= memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //회원가입 예외 로직
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
                //assertThrow 예외처리 방법

        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
                        //memberService.join(member2)를 실행할 때 IllegalStateException 이 예외가 터지도록 하는 것

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

                //try-catch 사용하여 예외 넣기
        /**
         * try{
         memberService.join(member2); //중복 회원 넣기
         fail();
         }catch(IlligalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //MemberService에 있는 validateDuplicateMember에 있는 예외 처리문
         }
         * */



        //then
    }

}