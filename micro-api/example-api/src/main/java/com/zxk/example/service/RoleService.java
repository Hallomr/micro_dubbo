package com.zxk.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxk.core.model.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zxk
 * @since 2022-04-25
 */
public interface RoleService extends IService<Role> {

    void insertRole();
}