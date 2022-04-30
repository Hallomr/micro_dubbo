package com.zxk.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxk.core.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxk
 * @since 2022-04-20
 */
public interface UserService extends IService<User> {

    User getUserInfo(Integer id);

    void batchInsert();

    void seataTransactionTest();
}