package com.example.secondcode.Dto;

import com.example.secondcode.Entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDto extends User {

    private Long id;

    private String password;

    @Override
    public String toString() {
        return "UserPo{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
