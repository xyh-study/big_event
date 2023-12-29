package com.njpi.xyh.mapper;

import com.njpi.xyh.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);


    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},now(),now())")
    void insertUser(String username, String password);


}
