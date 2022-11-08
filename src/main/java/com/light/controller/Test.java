package com.light.controller;

import com.light.mapper.UserMapper;
import com.light.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public String getTest(){
        List<User> users = userMapper.selectByMap(null);
        String s=null;
        for (User user : users) {
            s+=user.toString()+"//n";
        }
        return s;
    }
}
