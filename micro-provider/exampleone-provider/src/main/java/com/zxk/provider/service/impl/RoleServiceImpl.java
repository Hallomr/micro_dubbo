package com.zxk.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxk.core.model.Role;
import com.zxk.database.anno.DataSource;
import com.zxk.database.enums.DataSourceEnum;
import com.zxk.example.service.RoleService;
import com.zxk.provider.mapper.RoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zxk
 * @since 2022-04-25
 */
@DubboService
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    @DataSource(DataSourceEnum.DB2)
    public void insertRole() {
        Role role = new Role();
        role.setFroleId(1L);
        role.setFcreateTime(LocalDateTime.now());
        role.setFcreateId(1L);
        role.setFmodifyTime(LocalDateTime.now());
        role.setFmodifyId(1L);
        role.setFroleName("testName");
        this.getBaseMapper().insert(role);
    }
}