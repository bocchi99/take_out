package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author diao 2023/7/3
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
// annotations表示只处理RestController\Controller注解的类
@Component
@Slf4j
@ResponseBody// 将返回值封装成JSON数据
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        log.info(exception.getMessage());
        if (exception.getMessage().contains("Duplicate entry")) {
            String[] split = exception.getMessage().split(" ");// 以“ ”拆分字符串
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("失败！");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException exception) {
        return R.error(exception.getMessage());
    }
}
