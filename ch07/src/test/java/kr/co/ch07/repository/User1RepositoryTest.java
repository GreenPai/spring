package kr.co.ch07.repository;

import kr.co.ch07.entity.Child;
import kr.co.ch07.entity.Parent;
import kr.co.ch07.entity.User1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class User1RepositoryTest {

    @Autowired
    private User1Repository user1Repository;

    @Test
    void findUser1ByUid() {

        // given
        String uid = "a101";
        // when
        User1 user1 = user1Repository.findUser1ByUid(uid);
    }

    @Test
    void findUser1ByName() {

        List<User1> user1List = user1Repository.findUser1ByName("하이요");
        System.out.println(user1List);
    }

    @Test
    void findUser1ByNameNot() {
        List<User1> user1List = user1Repository.findUser1ByNameNot("홍길동");
        System.out.println(user1List);
    }

    @Test
    void testFindUser1ByUid() {
        System.out.println(user1Repository.findUser1ByUid("a1001"));
    }

    @Test
    void testFindUser1ByName() {
        System.out.println(user1Repository.findUser1ByName("홍길동"));
    }

    // 오류
    @Test
    void testFindUser1ByNameNot() {
        System.out.println(user1Repository.findUser1ByNameNot("홍길동"));
    }

    @Test
    void findUser1ByUidAndName() {
        System.out.println(user1Repository.findUser1ByUidAndName("a102","하이요"));
    }

    @Test
    void findUser1ByUidOrName() {
        System.out.println(user1Repository.findUser1ByUidOrName("a102","홍길동"));
    }

    @Test
    void findUser1ByAgeGreaterThan() {
        System.out.println(user1Repository.findUser1ByAgeGreaterThan(24));
    }

    @Test
    void findUser1ByAgeGreaterThanEqual() {
        System.out.println(user1Repository.findUser1ByAgeGreaterThanEqual(23));
    }

    @Test
    void findUser1ByAgeLessThan() {
        System.out.println(user1Repository.findUser1ByAgeLessThan(24));
    }

    @Test
    void findUser1ByAgeLessThanEqual() {
        System.out.println(user1Repository.findUser1ByAgeLessThanEqual(23));
    }

    @Test
    void findUser1ByAgeBetween() {
        System.out.println(user1Repository.findUser1ByAgeBetween(24,25));
    }

    // 오류
    @Test
    void findUser1ByNameLike() {
        System.out.println(user1Repository.findUser1ByNameLike("%홍길%"));
    }

    @Test
    void findUser1ByNameContains() {
        System.out.println(user1Repository.findUser1ByNameContains("홍길"));
    }

    @Test
    void findUser1ByNameStartsWith() {
        System.out.println(user1Repository.findUser1ByNameStartsWith("홍"));
    }

    @Test
    void findUser1ByNameEndsWith() {
        System.out.println(user1Repository.findUser1ByNameEndsWith("길동"));
    }

    @Test
    void findUser1ByOrderByName() {
        System.out.println(user1Repository.findUser1ByOrderByName());
    }

    @Test
    void findUser1ByNameOrderByUid() {
        System.out.println(user1Repository.findUser1ByNameOrderByUid("홍길동"));
    }

    @Test
    void findUser1ByOrderByAgeAsc() {
        System.out.println(user1Repository.findUser1ByOrderByAgeAsc());
    }

    @Test
    void findUser1ByOrderByAgeDesc() {
        System.out.println(user1Repository.findUser1ByOrderByAgeDesc());
    }

    @Test
    void findUser1ByAgeGreaterThanOrderByAgeDesc() {
        System.out.println(user1Repository.findUser1ByAgeGreaterThanOrderByAgeDesc(24));
    }

    @Test
    void countUser1ByName() {
        System.out.println(user1Repository.countUser1ByName("홍길동"));
    }

    @Test
    void selectUser1UnderAge30() {
        System.out.println(user1Repository.selectUser1UnderAge30());
    }

    @Test
    void selectUser1ByName() {
        System.out.println(user1Repository.selectUser1ByName("홍길동"));
    }

    @Test
    void selectUser1ByNameParam() {
        System.out.println(user1Repository.selectUser1ByNameParam("홍길동"));
    }

    @Test
    void selectUser1ByUid() {
        List<Object[]>  list = user1Repository.selectUser1ByUid("a1001");

        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    void selectParentWithChild() {

        // given
        String pid = "p101";
        List<Object[]> list = user1Repository.selectParentWithChild(pid);

        // then
        for(Object[] arr : list){
            Parent parent = (Parent) arr[0];
            Child child = (Child) arr[1];

            System.out.println(parent);
            System.out.println(child);
        }
    }
}