package com.zxk.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxk.core.model.User;
import com.zxk.database.anno.DataSource;
import com.zxk.database.enums.DataSourceEnum;
import com.zxk.example.service.RoleService;
import com.zxk.example.service.UserService;
import com.zxk.provider.mapper.UserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxk
 * @since 2022-04-20
 */
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @DubboReference
    private RoleService roleService;

    @Override
    @DataSource(DataSourceEnum.DB1)
    public User getUserInfo(Integer id) {
        return this.getBaseMapper().selectById(id);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public void batchInsert() {
        List<User> users = new ArrayList<>();
        User u1 = new User();
        u1.setId(3);
        u1.setUsername("u1");
        u1.setPassword("123456");
        User u2 = new User();
        u2.setId(4);
        u2.setUsername("u2");
        u2.setPassword("123456");
        users.add(u1);
        users.add(u2);
        this.getBaseMapper().insertBatchSomeColumn(users);
    }

    @Override
    @GlobalTransactional
    public void seataTransactionTest() {
        this.batchInsert();
        roleService.insertRole();
    }
}