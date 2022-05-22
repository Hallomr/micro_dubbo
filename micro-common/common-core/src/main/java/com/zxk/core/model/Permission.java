package com.zxk.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_permission")
public class Permission implements Serializable {
    private Integer id;
    private String name;
    private String descritpion;
    private String url;
    private Integer pid;
}
