package kr.co.ch06.controller;

import kr.co.ch06.service.User1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc // MockMvc를 주입하기 위한
@SpringBootTest
class Sub1ControllerTest {

    @Autowired
    private MockMvc mockMvc; // MVC 테스트를 위한 가상 mvc 객체

    @Test
    void hello() throws Exception {

        for(int i=0;i<1000;i++){
            mockMvc
                    .perform(get("/sub1/hello"))            // 요청 주소
                    .andExpect(status().isOk())             // 요청 결과 코드
                    .andExpect(view().name("/sub1/hello"))  // 요청 결과 페이지
                    .andDo(print());                        // 요청 테스트 결과 출력
        }

    }

    @Test
    void welcome() throws Exception {

        mockMvc
                .perform(get("/sub1/welcome")
                        .param("uid","a101")
                        .param("name","테스트"))            // 요청 주소
                .andExpect(status().isOk())                // 요청 결과 코드
                .andExpect(view().name("/sub1/welcome"))   // 요청 결과 페이지
                .andDo(print());                           // 요청 테스트 결과 출력

    }


}