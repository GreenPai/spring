package kr.co.greendae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/education")
    public String education(){
        return "/info/education";
    }

    @GetMapping("/engineering")
    public String engineering(){
        return "/info/engineering";
    }

    @GetMapping("/graduate")
    public String graduate(){
        return "/info/graduate";
    }

    @GetMapping("/natural")
    public String natural(){
        return "/info/natural";
    }

    @GetMapping("/social")
    public String social(){
        return "/info/social";
    }
}
