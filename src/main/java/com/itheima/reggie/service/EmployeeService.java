package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
     boolean isExist(String s);
}
