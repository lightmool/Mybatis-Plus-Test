package com.light;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.light.mapper.UserMapper;
import com.light.pojo.User;
import com.light.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    /**
     * 查询
     */
    @Test
    void test01(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","宇")
                .between("age",22,24)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 升降序查询
     */
    @Test
    void test02(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("age")
                .orderByDesc("uid");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 删除条件
     */
    @Test
    void test03(){
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        System.out.println(userMapper.delete(queryWrapper));
    }

    /**
     * 替换
     */
    @Test
    void test04(){
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",22)
                .or()
                .isNotNull("email");
        User user=new User();
        user.setName("2222");
        user.setEmail(user.getEmail());
        userMapper.update(user,queryWrapper);
    }

    /**
     *组合条件
     */
    @Test
    void test05(){
        //UPDATE t_user SET user_name=?, email=? WHERE isdelete=0 AND (user_name LIKE ? AND (age > ? OR email IS NOT NULL))
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","3")
                        .and(i-> i.gt("age",19)
                                .or()
                                .isNotNull("email"));
        User user=new User();
        user.setName("333");
        String email="";
        for (String s : UUID.randomUUID().toString().split("-")) {
            email+=s;
        }
        email+="@email.com";
        user.setEmail(email);
        userMapper.update(user,queryWrapper);
    }

    /**
     * 查询指定列
     */
    @Test
    void Test06(){
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.select("user_name","age","email")
                .eq("uid",1590211985888813057L);
//        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
//        for (Map<String, Object> map : maps) {
//            System.out.println(map.toString());
//        }
//        List<User> userList = userMapper.selectList(queryWrapper);
//        for (User user : userList) {
//            System.out.println(user);
//        }
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 子查询
     */
    @Test
    void Test07(){
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql(true,"age","select age from t_user");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 修改
     */
    @Test
    void Test08(){
        UpdateWrapper<User>userUpdateWrapper=new UpdateWrapper<>();
        userUpdateWrapper.set("user_name","2222222")
                        .eq("uid",1590211985981087745L);
        User user=new User();
        user.setAge(11);
        user.setEmail("1233333333333333");
        userService.update(user,userUpdateWrapper);
    }
}
