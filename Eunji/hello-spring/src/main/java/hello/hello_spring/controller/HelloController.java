package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string") //이걸 쓸일 거의 없음
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
