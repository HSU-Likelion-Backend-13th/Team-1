package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        //templates에서 data부분을 hello!!로 매핑 (키-값)
        return "hello";
        //templates 내부의 hello.html을 리턴 값으로 지정
    }
}
