package com.zxk.consumer.controller;

import com.zxk.core.model.User;
import com.zxk.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxk
 * @since 2022-04-20
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @DubboReference
    private UserService userService;

    @GetMapping(value = "/userInfo/{id}")
    public void userInfo(@PathVariable(value = "id")Integer id){
        User user = userService.getUserInfo(id);
        log.info("userInfo:{}",user);
    }

    @GetMapping(value = "/batchInsert")
    @ApiOperation(value = "测试mybatisplus自定义批量插入---mysql")
    public void batchInsert(){
        userService.batchInsert();
    }

    @GetMapping(value = "/seata")
    @ApiOperation(value = "seata分布式事务 at模式测试")
    public void seataTransaction(){
        userService.seataTransactionTest();
    }
}