package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee")// 这里添加url
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee) {
        String username = employee.getUsername();
        String pwd = employee.getPassword();


        // 查询用户是否存在
        boolean exist = employeeServiceImpl.isExist(username);
        if (!exist) {
            return R.error("该用户不存在");
        }
        Employee emp = employeeServiceImpl.getEmployee(username);
        String password = emp.getPassword();
        if (!password.equals(pwd)) {
            return R.error("密码不正确");
        }
        if (emp.getStatus() != 1) {
            return R.error("账号被禁用");
        }
        return R.success(employee);
        // ???返回实体
    }

    @PostMapping
    public R addEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        employee.setPassword("123456");
        employeeServiceImpl.save(employee);
        return R.success("添加成功！");
    }

    @GetMapping("/page")
    // Page对象里面不光有List集合，还有总的记录条数
    public R<Page<Employee>> getPage(int page, int pageSize, String name) {
        Page<Employee> objectPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(null != name, Employee::getName, name);// 多条件查询
        Page<Employee> page1 = employeeServiceImpl.page(objectPage, lambdaQueryWrapper);
        return R.success(page1);
    }

    @PutMapping
    public R<String> updateStatus(HttpServletRequest request, @RequestBody Employee employee) {
        employeeServiceImpl.updateById(employee);

        return R.success("更改成功！");
    }

    @GetMapping("/{id}")
    public R<Employee> updateEmployee(@PathVariable Long id) {
        Employee employee = employeeServiceImpl.getById(id);
        return R.success(employee);
    }

}
