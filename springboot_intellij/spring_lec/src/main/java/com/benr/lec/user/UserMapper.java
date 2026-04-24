package com.benr.lec.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from login_test")
    List<UserVO> selectAll();

    @Delete("delete from login_test where l_id = #{id}")
    void deleteUser(String id);

    @Insert("insert into login_test values (#{l_id}, #{l_name}, #{l_pw})")
    void insertUser(UserVO userVO);

    @Select("select * from login_test where l_id = #{id}")
    UserVO selectUser(String id); // UserVO로 반환 이유?

}
