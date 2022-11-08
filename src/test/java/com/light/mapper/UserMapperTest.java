package com.light.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.light.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 增加一个user
     */
    @Test
    void insertUser(){
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        for (int i = 20; i < 30; i++) {
            User user=new User();
            user.setName("童宇");
            user.setAge(i);
            user.setEmail("lightmool261@gmail.com");
            int result = userMapper.insert(user);
            System.out.println("result="+result);
            //雪花算法自动创建ID
            //1589897901819600897
            System.out.println("id="+user.getId());
        }
    }

    /**
     * 删除一个user
     */
    @Test
    void deleteUser(){
        //DELETE FROM user WHERE id=?
        //1589897901819600897超出范围需要加L
        int deleteById = userMapper.deleteById(1589897901819600897L);
        System.out.println("result="+deleteById);

        //DELETE FROM user WHERE name = ? AND age = ?
        //根据map集合进行数据删除
        Map<String,Object>map=new HashMap<>();
        map.put("name","童宇");
        map.put("age",22);
        int deleteByMap = userMapper.deleteByMap(map);
        System.out.println("result="+deleteByMap);

        //DELETE FROM user WHERE id IN ( ? , ? , ? , ? )
        List<Long> ids = Arrays.asList(2L, 3L, 4L, 5L);
        int deleteBatchIds = userMapper.deleteBatchIds(ids);
        System.out.println("result="+deleteBatchIds);
    }

    /**
     * 修改一个用户
     */
    @Test
    void UpdateUser(){
        // UPDATE user SET name=?, age=?, email=? WHERE id=?
        User user = new User();
        user.setName("柏霖");
        user.setAge(222);
        user.setEmail("bolin2222g@gmail.cpm");
        int updateById = userMapper.updateById(user);
        System.out.println("result="+updateById);
        //UPDATE user SET name=?, age=?, email=? WHERE age=?
        User user1 = new User();
        user1.setAge(22);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.setEntity(user1);
        int update = userMapper.update(user, userUpdateWrapper);
    }

    /**
     * 查询用户
     */
    @Test
    void setUser(){

//        //通过ID查询
//        //SELECT id,name,age,email FROM user WHERE id=?
//        User user = userMapper.selectById(1589903008451878914L);
//        //通过IDS进行批量查询
//        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? , ? , ? )
//        List<Long>ids=new ArrayList<>();
//        ids.add(1589903006543470594L);
//        ids.add(1589903008359604225L);
//        ids.add(1589903008367992833L);
//        ids.add(1589903008384770050L);
//        ids.add(1589903008401547265L);
//        List<User> users = userMapper.selectBatchIds(ids);
//
//        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
//        Map<String,Object>map=new HashMap<>();
//        map.put("name","柏霖");
//        map.put("age",222);
//        List<User> users1 = userMapper.selectByMap(map);
//
//        //SELECT id,name,age,email FROM user
//        List<User> users2 = userMapper.selectList(null);

        Map<String, Object> stringObjectMap = userMapper.selectMapById(1589903008451878914L);
        System.out.println(stringObjectMap);
    }
}
