package com.zxk.core.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    @ExcelProperty(value = "用户密码",index = 1)
    private String password;

    @ExcelProperty(value = "用户名",index = 0)
    private String username;

}