package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author huanghua 2023/7/5
 */
/*
 方法一：不影响其它方法的使用的情况下，将@Controller注解改为@RestController

方法二：在需要请求的返回json对象的方法上添加@ResponseBody注解

本人项目中该方法所在类的其它方法有的返回到指定页面，所以不能用@RestController直接替换@Controller，所以采用方法二解决了问题。
*/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    public CategoryServiceImpl categoryServiceImpl;

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    // @ResponseBody// 表明返回json对象
    public R<Page<Category>> page(int page, int pageSize) {
        Page<Category> page1 = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        Page<Category> categoryPage = categoryServiceImpl.page(page1, lambdaQueryWrapper);
        return R.success(categoryPage);
    }

    /**
     * 添加菜品\套餐分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public R<String> addCategory(@RequestBody Category category) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getName, category.getName());
        int count = categoryServiceImpl.count(lambdaQueryWrapper);
        if (count == 0) {
            categoryServiceImpl.save(category);
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 根据id删除分类(需要判断是否关联了其他菜品,因为菜品表中引用了套餐/分类这个外键)
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public void deleteCategory(long id) {
        /*categoryServiceImpl.removeById(id);
        return R.success("删除成功!");*/
        categoryServiceImpl.removeBeforeJudge(id);
    }

    // ==>从这里开始
    @PutMapping
    public R<String> updateCategory(@RequestBody Category category) {
        categoryServiceImpl.updateById(category);
        return R.success("更新成功!");
    }
}
