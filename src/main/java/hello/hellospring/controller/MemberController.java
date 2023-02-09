package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //멤버 컨트롤러가 멤버 서비스를 통해 회원가입을 하고, 데이터를 조회할 수 있어야 함 -> 멤버 컨트롤러가 멤버서비스를 의존

    /**
     private final MemberService memberService = new MemberService();
     로 해놓으면 다른 컨트롤러에서도 계속 new를 사용하여 여러 개를 생성하여 사용함.
     근데 그럴 필요가 없음. MemberService 하나만 생성해서 공유하여 사용해도 됨

     스프링 컨테이너에 등록하여 사용
     * */
    private final MemberService memberService;

   //이 멤버 컨트롤러, 스프링 컨테이너가 뜰 때 생성이 되는데, 그러면 이 때, 이 생성자를 호출하는데 @Autowired가 되어 있으면
    //스프링이 스프링 컨테이너에 있는 멤버 서비스를 갖다가 연결해준다/ 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    //get은 주로 조회할때 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //post는 주로 데이터를 폼 같은 곳에 넣어 전달할 때
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
