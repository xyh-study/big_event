package com.njpi.xyh.service;

import com.njpi.xyh.domain.User;
import org.springframework.stereotype.Service;



public interface UserService {
    User findUserByUserName(String username);

    void register(String username, String password);

}
