package com.example.secondcode.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@TableName("user")
//@ApiModel("用户实体")
public class User {
    //    id
    @TableId
    //@ApiModelProperty("主键ID")
    private Long id;
    //    姓名
    @TableField("username")
    //@ApiModelProperty("用户姓名")
    private String username;
    //    密码
    @TableField("password")
    //@ApiModelProperty("用户密码")
    private String password;
    //    地区
    @TableField("area")
    //@ApiModelProperty("用户所在区域")
    private String area;
    //    电话
    @TableField("phone")
    //@ApiModelProperty("用户联系电话")
    private String phone;
    //    类型
    @TableField("type")
    //@ApiModelProperty("用户类型")
    private String type;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", area='" + area + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
