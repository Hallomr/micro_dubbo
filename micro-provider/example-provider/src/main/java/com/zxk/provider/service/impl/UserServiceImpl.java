package com.zxk.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxk.core.model.User;
import com.zxk.database.anno.DataSource;
import com.zxk.database.enums.DataSourceEnum;
import com.zxk.example.service.RoleService;
import com.zxk.example.service.UserService;
import com.zxk.provider.mapper.UserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
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
@Slf4j
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

    /**
     *  todo 多数据源@Transactional事物失效
     *  方案一（使用多数据源插件dynamic-datasource-spring-boot-starter）：
     *  https://blog.csdn.net/Wu_Shang001/article/details/121182437
     *  方案二（自定义事物切面）：
     *  https://blog.csdn.net/qq_42346678/article/details/123372054
     * */
    @Override
    @DataSource(DataSourceEnum.DB1)
    //@Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<User> users) {
        long t1,t2;
        t1 = System.currentTimeMillis();
        this.getBaseMapper().insertBatchSomeColumn(users);
        t2 = System.currentTimeMillis();
        log.info("批量入库耗时：{}",t2-t1);
    }

    @Override
    @GlobalTransactional
    public void seataTransactionTest() {
        this.batchInsert();
        roleService.insertRole();
    }
}