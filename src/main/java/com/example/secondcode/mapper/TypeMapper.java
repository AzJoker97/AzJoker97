package com.example.secondcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.secondcode.Entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TypeMapper extends BaseMapper<Type> {


    public List<Type> findType();

}
