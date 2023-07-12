package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public boolean isExist(String s) {
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, s);
        int count = employeeMapper.selectCount(lambdaQueryWrapper);
        if (count == 0) {
            return false;// 不存在
        } else {
            return true;// 存在
        }
    }

    public Employee getEmployee(String userName) {
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, userName);
        Employee employee = employeeMapper.selectOne(lambdaQueryWrapper);
        return employee;
    }
    // 根据id查找到员工，然后更新账号状态
   /* public void updateStatus(Long id,int status){
        Employee employee = employeeMapper.selectById(id);
        employee.setStatus(status);
    }*/
}
