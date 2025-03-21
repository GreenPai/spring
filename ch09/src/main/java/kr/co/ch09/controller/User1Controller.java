package kr.co.ch09.controller;

import jakarta.validation.Valid;
import kr.co.ch09.dto.User1DTO;
import kr.co.ch09.servcie.User1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class User1Controller {

    private final User1Service user1Service;

    /*
    *   @ResponseBody는 메서드의 반환값을 응답객체 본문으로 출력하기 위한 어노테이션,
    *   자동 JSON 변환 출력
    * */

    @ResponseBody
    @GetMapping("/user1")
    public List<User1DTO> list() {
        return user1Service.findAll();
    }

    @ResponseBody
    @GetMapping("/user1/{uid}")
    public User1DTO user(@PathVariable("uid") String uid){ // @PathVariable 주소 파라미터 값을 바인딩하기 위한 어노테이션
        return user1Service.findById(uid);
    }

    /*
    * RequestBoy : 요청본문 내용에 포함된 데이터를 Java 객체로 반환하는 어노테이션
    *
    * @Vaild
    *  - REST API 서비스 특성상 클라이언트에서 유효성검사를 하기 어렵기 때문에 백엔드에서 유효성 검사를 수행
    *  - 수신 처리되는 DTO의 유효성 검사 어노테이션 (@NotBlank, @NotEmpty, @NotNull, @Email...)을 사용
    *  - @Vaild 어노테이션으로 유효성검사 어노테이션을 수행
    * */
    @PostMapping("/user1")
    public ResponseEntity<User1DTO> register(@Valid @RequestBody User1DTO user1DTO) {

        log.info("user1DTO : {}", user1DTO);

        User1DTO savedUser1 =  user1Service.save(user1DTO);

        return ResponseEntity.ok(savedUser1);
    }

    @PutMapping("/user1")
    public ResponseEntity<User1DTO> modify(@RequestBody User1DTO user1DTO) {
        User1DTO modifiedUser1 =  user1Service.modify(user1DTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifiedUser1);
    }

    @DeleteMapping("/user1/{uid}")
    public ResponseEntity<String> delete(@PathVariable("uid") String uid) {
        boolean isDeleted = user1Service.delete(uid);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }


}
