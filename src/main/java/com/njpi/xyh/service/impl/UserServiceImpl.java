package com.njpi.xyh.service.impl;

import com.njpi.xyh.domain.User;
import com.njpi.xyh.mapper.UserMapper;
import com.njpi.xyh.service.UserService;
import com.njpi.xyh.utils.Md5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User findUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }


    @Override
    public void register(String username, String password) {
        // 加密密码
         password = Md5Util.getMD5String(password);
        // 添加
        userMapper.insertUser(username,password);
    }




}
