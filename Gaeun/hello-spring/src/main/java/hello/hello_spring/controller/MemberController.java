package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//해당 클래스가 컨트롤러임을 나타냄
//해당 클래스를 빈으로 등록하고 요청을 해당 컨크롤러에 매핑하여 처리
public class MemberController {

    //여러 컨트롤러에서 사용할시, 여러개의 MemberService를 생성하기보다는 하나만 생성해서 공용으로 사용
    //스프링 컨테이너에 등록해서 사용
    private final MemberService memberService;

    //의존관계 주입(DI) : 생성자 주입을 권장
    @Autowired //스프링이 스프링 컨테이너에 있는 멤버 서비스 객체를 가져와서 생성자에 연결
    //클래스가 스프링 빈으로 등록이 안되어있다면 작동하지 않는다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //조회시 Get 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm.html";
    }

    //데이터 등록시 Post 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; //홈 화면으로 이동
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberlist";

    }
}
