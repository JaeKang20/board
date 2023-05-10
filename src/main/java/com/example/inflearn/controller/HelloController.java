package com.example.inflearn.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
//MVC에서 Controller를 맡은 부분입니다.

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","Hello!");
        return "hello";
    }
    @GetMapping("hello-mvc")//템플릿 엔진
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
        //http://localhost:8080/hello-mvc?name=(아무것이나) 에서 실행됩니다.
        //attributeName은 Model의 키값. html파일에서 떼오는것.
        //name은 요청값. 주소에 쓸 값을 의미.
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http 바디에 직접 넣어줘 (CRUD에 적합)
    //이 어노테이션 말고 @RestController를 써서 따로 API응답을 하기도 합니다.
    //뷰가 없습니다.
    public String helloString(@RequestParam("name")String name){
        return "hello" + name;
    }//문자열 반환은 아예 안씁니다. 객체를 리턴하는걸 많이 씁니다.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){//HelloDto를 전달.
        Hello hello = new Hello();
    hello.setName(name);
    return hello;
    }
    //responseBody를 문자열이 아닌 객체를 전달한 방법으로 이 방법은
     //JSON을 출력하게 해주는 방법입니다.
    @Getter@Setter
    static class Hello{
    private String name;

    }
    }
