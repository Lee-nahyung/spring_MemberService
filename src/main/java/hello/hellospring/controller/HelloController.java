package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); /**키는 data고 값은 hello임**/
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        /**
        *파라미터를 넘겨주도록 해야 하니까
         * localhost:8080/hello-mvc만 해서는 안됨 파라미터 필요
         * localhost:8080/hello-mvc?name = 하고 파라미터
         * ex) lcoalhost:8080/hello-mvc?name = spring
         */
    }

    
    //이 방식은 잘 안 씀
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello "+ name; //name에 spring 넣으면 "hello spring"
        //이대로 데이터를 넣어줌, html ㄴㄴ
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}