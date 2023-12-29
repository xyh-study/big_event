package com.njpi.xyh.controller;


import com.auth0.jwt.JWT;
import com.njpi.xyh.common.Result;
import com.njpi.xyh.domain.User;
import com.njpi.xyh.service.UserService;
import com.njpi.xyh.utils.JwtUtil;
import com.njpi.xyh.utils.Md5Util;
import com.njpi.xyh.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestParam @Size(min = 6,max = 10,message = "用户名长度为6-10") String username,
                           @RequestParam  @Size(min = 6,max = 10,message = "密码长度为6-10") String password) {
        // 查询用户
        User user = userService.findUserByUserName(username);

        if(user== null){
            userService.register(username,password);
            return Result.success();
        }
        else {
            return Result.error("用户已存在");
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestParam  @Size(min = 6,max = 10,message = "用户名长度为6-10") String username,
                        @RequestParam @Size(min = 6,max = 10,message = "密码长度为6-10") String password){
        User userByUserName = userService.findUserByUserName(username);
        if(userByUserName == null){
                return Result.error("用户名错误");
        }
        boolean b = Md5Util.checkPassword(password, userByUserName.getPassword());
        if(!b){
            return Result.error("密码错误");
        }
        // 生成token
        HashMap<String, Object> params = new HashMap<>();
        params.put("id",userByUserName.getId());
        params.put("username",userByUserName.getUsername());

        String token = JwtUtil.genToken(params);
        return Result.success(token);
    }

    /**
     * 获取当前登录的用户信息你
     * @return
     */
    @GetMapping("userinfo")
    public Result userinfo(){
        // 用户信息
        HashMap hashMap = ThreadLocalUtil.get();
        // 当前登录的用户名
        String username = (String) hashMap.get("username");
        User userByUserName = userService.findUserByUserName(username);

        return Result.success(userByUserName);
    }

    // TODO 用户修改
}



