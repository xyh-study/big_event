package com.njpi.xyh.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    @NotNull
    private String username;//用户名
    @NotNull
    private String password;//密码

    @NotEmpty
    @Size(min = 1,max = 10,message = "昵称长度为1-10个字符")
    private String nickname;//昵称

    @NotEmpty
    @Email(message = "邮箱格式错误")
    private String email;//邮箱

    @NotEmpty(message = "用户邮箱不可以为空")
    private String userPic;//用户头像地址

    @NotNull
    private LocalDateTime createTime;//创建时间

    @NotNull
    private LocalDateTime updateTime;//更新时间
}
