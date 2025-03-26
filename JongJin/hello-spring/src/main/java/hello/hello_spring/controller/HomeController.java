package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    // 계속 index.html이 뜸
    // 질문게시판을 확인한 후 -> "캐시 삭제" 하니깐 정상 작동함.
    // "웹 브라우저의 캐시가 이전 상태를 유지하고 있는 경우가 있습니다." 라고 적혀있었다.
    // 어렵다...
    public String home() {
        return "home";
    }
}
