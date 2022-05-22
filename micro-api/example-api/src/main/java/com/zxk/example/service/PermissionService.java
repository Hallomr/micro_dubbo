package com.zxk.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxk.core.model.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> findByUserId(Integer id);
}
