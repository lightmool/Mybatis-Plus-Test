package com.light.service;

import com.light.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;


    /**
     * 查询总记录数
     */
    @Test
    void getAllList(){
        //SELECT COUNT( * ) FROM user
        System.out.println(userService.count());
    }

    /**
     * 批量添加用户
     */
    @Test
    void saveUser(){
        List<User> userList=new ArrayList<>();
        int age=20;
        for (int i = 0; i < 20; i++) {
            User user=new User();
            user.setAge(age+i);
            user.setName("童宇"+i);
            userList.add(user);
        }
        System.out.println(userService.saveBatch(userList));
    }
}
