package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campus")
public class CampusController {

    @GetMapping("/club_study")
    public String clubStudy(){
        return "/campus/club_study";
    }

}
