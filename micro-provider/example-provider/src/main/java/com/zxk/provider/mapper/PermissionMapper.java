package com.zxk.provider.mapper;

import com.zxk.core.model.Permission;
import com.zxk.database.anno.MapperFilter;

import java.util.List;

public interface PermissionMapper extends RootMapper<Permission> {
    @MapperFilter
    List<Permission> findByUserId(Integer id);
}
