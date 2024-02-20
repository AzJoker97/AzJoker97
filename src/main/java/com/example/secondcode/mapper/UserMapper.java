package com.example.secondcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.secondcode.Entity.User;
import com.example.secondcode.Vo.UserVo;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper extends BaseMapper<User> {

//    public UserVo getUserById(long id);
}
