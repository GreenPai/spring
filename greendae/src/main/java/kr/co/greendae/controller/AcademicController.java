package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/academic")
public class AcademicController {

    @GetMapping("/schedule")
    public String schedule(){
        return "/academic/schedule";
    }

    @GetMapping("/graduation")
    public String graduation(){
        return "/academic/graduation";
    }

    @GetMapping("/registration")
    public String registration(){
        return "/academic/registration";
    }


}
