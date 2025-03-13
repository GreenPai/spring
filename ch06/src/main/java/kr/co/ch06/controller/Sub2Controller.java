package kr.co.ch06.controller;

import kr.co.ch06.dto.User1DTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class Sub2Controller {

    @GetMapping("/sub2/thymeleaf")
    public String thymeleaf(Model model) {

        String str1 = "Hello World";
        String str2 = "Hello Thymeleaf";

        // User1DTO 객체 생성 - 생성자 방식
        User1DTO user1 = new User1DTO("a101","김유신","010-2121-1001",21);
        log.info("user1={}", user1);

        // User1DTO 객체 생성 - 세터 방식
        User1DTO user2 = new User1DTO();
        user2.setUid("a102");
        user2.setName("김준호");
        user2.setHp("010-1212-1002");
        user2.setAge(18);
        log.info("user2={}", user2);

        // User1DTO 객체 생성 - 빌더 방식
        User1DTO user3 = User1DTO.builder()
                            .uid("a103")
                            .name("킴준호")
                            .hp("010-1212-1003")
                            .age(23)
                            .build();

        log.info("user3={}", user3);

        // 리스트 생성
        List<User1DTO> users = List.of(user1,user2,user3);

        // 모델 참조
        model.addAttribute("str1", str1);
        model.addAttribute("str2", str2);

        model.addAttribute("user1", user1);
        model.addAttribute("user2", user2);
        model.addAttribute("user3", user3);
        model.addAttribute("users", users);
        return "/sub2/thymeleaf";

    }
}
