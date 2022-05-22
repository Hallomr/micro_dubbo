package com.zxk.consumer.controller;

import com.alibaba.excel.EasyExcel;
import com.zxk.core.dto.UserDto;
import com.zxk.core.model.Permission;
import com.zxk.core.model.User;
import com.zxk.core.utils.UploadDataListener;
import com.zxk.example.service.PermissionService;
import com.zxk.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @DubboReference
    private PermissionService permissionService;

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

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UserDto.class, new UploadDataListener(userService)).sheet().doRead();
        return "success";
    }

    @RequestMapping("/permission")
    public List<Permission> user(Integer id) {
        return permissionService.findByUserId(id);
    }
}