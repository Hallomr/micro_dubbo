package com.zxk.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxk.core.model.Permission;
import com.zxk.database.anno.DataSource;
import com.zxk.database.enums.DataSourceEnum;
import com.zxk.example.service.PermissionService;
import com.zxk.provider.mapper.PermissionMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @DataSource(DataSourceEnum.DB1)
    public List<Permission> findByUserId(Integer id) {
        return permissionMapper.findByUserId(id);
    }
}
