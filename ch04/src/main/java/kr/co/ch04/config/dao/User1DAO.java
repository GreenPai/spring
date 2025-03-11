package kr.co.ch04.config.dao;

import kr.co.ch04.config.dto.User1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class User1DAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser1(User1DTO dto){

        String sql =  "insert into user1 values (?,?,?,?)";

        Object[] parmas = {
                dto.getUid(),
                dto.getName(),
                dto.getHp(),
                dto.getAge()
        };

        jdbcTemplate.update(sql,parmas);
    }

    public User1DTO selectUser1(String uid){

        String sql =  "select * from user1 where uid=?";
        Object[] parmas = {uid};

        return jdbcTemplate.queryForObject(sql, new User1RowMapper(), parmas);
    }

    public List<User1DTO> selectAllUser1(){

        String sql =  "select * from user1";
        return jdbcTemplate.query(sql, new User1RowMapper());
    }

    public void updateUser1(User1DTO dto){

        String sql = "update user1 set name=?,hp=?,age=? where uid=?";
        Object[] parmas = {
                dto.getName(),
                dto.getHp(),
                dto.getAge(),
                dto.getUid()
        };
        jdbcTemplate.update(sql,parmas);
    }

    public void deleteUser1(String uid){
        String sql = "delete from user1 where uid=?";
        Object[] parmas = {uid};

        jdbcTemplate.update(sql,parmas);
    }


}
