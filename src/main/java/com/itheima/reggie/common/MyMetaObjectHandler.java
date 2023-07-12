package com.itheima.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author huanghua 2023/7/3
 */
@Component// 把一个普通Java类但成Bean注入进spring容器
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入字段自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段字段填充......");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        long id = Thread.currentThread().getId();// 怎么获取当前用户id
        metaObject.setValue("createUser",id);
        metaObject.setValue("updateUser",id);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",LocalDateTime.now());
        long id = Thread.currentThread().getId();
        log.info("用户id"+id);
        metaObject.setValue("updateUser",id);

    }
}
