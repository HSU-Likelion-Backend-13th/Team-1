package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello") //웹앱에서 /hello라고 들어오면 이 메서드를 호출해줌(스프링이) Get은 Get,Post할때의 Get이다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //모델은 MVC의 M
        return "hello"; //hello.html찾아서 걔한테가서 렌더링
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) { //외부에서 파라미터 받는 경우 , required = false일 시 값을 넘기지 않아도됨
        model.addAttribute("name", name);
        return "hello-template";
    }
}
