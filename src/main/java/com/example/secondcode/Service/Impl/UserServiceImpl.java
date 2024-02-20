package com.example.secondcode.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.secondcode.Entity.User;
import com.example.secondcode.Service.UserService;
import com.example.secondcode.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

}
