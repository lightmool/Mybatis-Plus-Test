package com.light.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.mapper.UserMapper;
import com.light.pojo.User;
import com.light.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
