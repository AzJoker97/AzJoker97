package com.example.secondcode.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.secondcode.Entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserVo {
//    ID
    @TableField("id")
    private  Long id;
// 类型
    @TableField("type")
    private String type;


    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
