package com.example.secondcode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.secondcode.Entity.Type;
import com.example.secondcode.Entity.User;
import com.example.secondcode.Dto.UserDto;
import com.example.secondcode.Service.TypeService;
import com.example.secondcode.Service.UserService;
import com.example.secondcode.Configs.MessageInfo;
import com.example.secondcode.Vo.UserVo;
import com.example.secondcode.mapper.TypeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理",description = "用户控制层")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeMapper typeMapper;

    /*id查询*/
    @GetMapping("/id")
    @Operation(summary = "根据id查询所有用户信息",description = "用户单体查询")
    @ApiResponse(responseCode = "404",description = "404请求地址错误")
    public User getUserById(@RequestParam Integer id) {
        User user = userService.getById(id);
        return user;
    }

    /*页面查询*/
    @GetMapping("/page")
    @Operation(summary = "展示所有用户信息",description = "用户展示")
    @ApiResponse(responseCode = "404",description = "404请求地址错误")
    public List<User> listUser() {
        List<User> userList = userService.list();
        return userList;
    }

    /*注册*/
    @PostMapping("/register")
    @Operation(summary = "新增用户信息",description = "用户注册")
    @ApiResponse(responseCode = "404",description = "404请求地址错误")
    public String registerUser(@RequestBody User user) {
        Boolean addT = userService.save(user);
        if (addT == true) {
            return ("注册成功");
        } else {
            return ("注册失败");
        }
    }

    /*登录*/
    @PostMapping("/login")
    @Operation(summary = "Id登录",description = "用户登录")
    @ApiResponse(responseCode = "404",description = "404请求地址错误")
    public UserVo loginUser(@RequestBody UserDto userDto) {
        Long id = userDto.getId();
        String password = userDto.getPassword();
        QueryWrapper<User> qe = new QueryWrapper<>();
        qe.eq("id", id);
        User user = userService.getOne(qe);
//        UserVo userVo1 = userMapper.getUserById(userDto.getId());
        if (userDto.getPassword() != null && userDto.getPassword().equals(user.getPassword())){
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return userVo;
        } else {
            return null;
        }
    }

    /*修改*/
    @PostMapping("/update")
    @Operation(summary = "id修改",description = "用户修改")
    @ApiResponse(responseCode = "404",description = "404请求地址错误")
    public String updateUser(@RequestBody User user){
        MessageInfo messageInfo = new MessageInfo();
//        QueryWrapper<User> qw = new QueryWrapper<>();
//        qw.eq("id",user.getId());
        userService.updateById(user);
        return messageInfo.Sucessful();
    }


    /*上传文件*/
    @PostMapping("/upload")
    @Operation(summary = "文件上传",description = "minio文件上传")
    public String uploadFile(@RequestBody MultipartFile file){
        return ("上传成功");
    }


    /**
    * @Description: 两张表查询
    * @Param:
    * @return:
    * @Author: Login-Moon
    * @Date: 2024/2/20
    */
    @GetMapping("/pageType")
    public List<Type> listType(){
        List<Type> typeList = typeService.list();
//        Type type = typeMapper.findType(1l);
        log.info("BUG");
        return typeList;
    }
}
