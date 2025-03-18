package kr.co.ch07.repository;

import jakarta.transaction.Transactional;
import kr.co.ch07.entity.board.Article;
import kr.co.ch07.entity.board.Comment;
import kr.co.ch07.entity.board.File;
import kr.co.ch07.entity.board.User;
import kr.co.ch07.repository.board.ArticleRepository;
import kr.co.ch07.repository.board.CommentRepository;
import kr.co.ch07.repository.board.FileRepository;
import kr.co.ch07.repository.board.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/*
* Board 관련 통합 테스트
* */
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private FileRepository fileRepository;
    @Autowired private UserRepository userRepository;

    // 테스트1 - 사용자 등록
    @Test
    public void test1(){
        
        // given - 테스트 데이터 준비
        User user = User.builder()
                .uid("a102")
                .name("홍유신")
                .hp("010-1234-1002")
                .build();

        // when - 테스트 실행
        User savedUser = userRepository.save(user);

        // then - 테스트 검증
        System.out.println(savedUser);

    }

    // 테스트2 - 글 등록
    @Test
    public void test2(){

        User user = User.builder()
                .uid("a102")
                .build();

        Article article = Article.builder()
                            .title("제목입니다3.")
                            .content("내용입니다.")
                            .user(user)
                            .build();

        Article savedArticle = articleRepository.save(article);
        System.out.println(savedArticle);


    }

    // 테스트3 - 댓글 등록
    @Test
    public void test3(){

        User user = User.builder()
                .uid("a102")
                .build();

        Article article = Article.builder()
                .no(1)
                .build();

        Comment comment = Comment.builder()
                .article(article)
                .content("댓글1 입니다")
                .user(user)
                .build();

        Comment savedComment = commentRepository.save(comment);
        System.out.println(savedComment);
    }

    // 테스트4 - 파일 등록
    @Test
    public void test4(){

        Article article = Article.builder()
                .no(1)
                .build();

        File file = File.builder()
                .article(article)
                .oName("파일이름1")
                .sName("파일이름2")
                .build();
        
        File savedFile= fileRepository.save(file);
        System.out.println(savedFile);

    }

    // 테스트5 - 전체 글 조회
    @Transactional
    @Test
    public void test5(){

        /*
         엔티티가 관계 설정으로 되어있을 경우에 해당 엔티티를 조회할 때 관계가
         맺어진 다른 엔티티도 함께 조회하기 때문에 단일 트랜잭션으로 처리해야
         된다. 그렇지 않으면 no session 에러 발생한다. -> Transactional 어노테이션 설정

         findAll -> select 1
         entity 관계 설정 -> select 2
        */
        List<Article> articleList = articleRepository.findAll();

        /*
        *   양방향(@OneToMany) 엔티티 관계에서 @ToString 어노테이션으로 생성되는 toString 메서드에서
        *   엔티티간 toString 재귀호출로 인해 stackOverFlow 에러 발생 -> @ToString에서 exclude 속성으로
        *   순환 참조 되는 엔티티를 제외
        *
        * */


        /*
        * System.out.println(article)
        * -> System.out.println(article.toString())
        * 
        * article -> Comment.toString 호출
        * Comment -> article.toString 호출
        * 무한반복 -> SlackOverflow 발생
        * */

        for (Article article : articleList) {
            System.out.println(article);
            List<Comment> commentList = article.getComment();

            for (Comment comment : commentList) {
                System.out.println(comment);
            }

        }

    }

    @Transactional
    // 테스트6 - 글 조회
    @Test
    public void test6(){

        Optional<Article> optArticle = articleRepository.findById(1L);

        if(optArticle.isPresent()){
            Article article = optArticle.get();
            System.out.println(article);
            List<Comment> commentList = article.getComment();
            for (Comment comment : commentList) {
                System.out.println(comment);
            }
        }

    }

}
