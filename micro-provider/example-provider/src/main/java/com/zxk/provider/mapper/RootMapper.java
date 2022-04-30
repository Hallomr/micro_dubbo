package com.zxk.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface RootMapper<T> extends BaseMapper<T> {
    /**
     * 批量插入，仅使用批量插入
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}