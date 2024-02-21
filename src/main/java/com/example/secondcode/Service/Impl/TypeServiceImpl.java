package com.example.secondcode.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.secondcode.Entity.Type;
import com.example.secondcode.Service.TypeService;
import com.example.secondcode.mapper.TypeMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
