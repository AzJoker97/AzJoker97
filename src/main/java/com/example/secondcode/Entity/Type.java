package com.example.secondcode.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type")
public class Type {

    @TableId(value = "type")
    private Long type;

    private String duties;

    @Override
    public String toString() {
        return "Type{" +
                "type=" + type +
                ", duties='" + duties + '\'' +
                '}';
    }
}
