package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class IntroController {

    @GetMapping("/greetings")
    public String greetings(){
        return "/intro/greetings";
    }

    @GetMapping("/history")
    public String history(){
        return "/intro/history";
    }

    @GetMapping("/location")
    public String location(){
        return "/intro/location";
    }

    @GetMapping("/organizationchart")
    public String organizationchart(){
        return "/intro/organizationchart";
    }

    @GetMapping("/philosophy")
    public String philosophy(){
        return "/intro/philosophy";
    }

    @GetMapping("/sexual")
    public String sexual(){
        return "/intro/sexual";
    }
}
