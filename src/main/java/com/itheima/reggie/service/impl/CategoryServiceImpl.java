package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.mapper.SetmealMapper;
import com.itheima.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huanghua 2023/7/5
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void removeBeforeJudge(Long id) {
        // 查询是否关联菜品
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId, id);
        Integer count1 = dishMapper.selectCount(dishQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("关联了菜品不能删除!");
        }
        // 查询是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId, id);
        Integer count2 = setmealMapper.selectCount(setmealQueryWrapper);

        if (count2 > 0) {
            throw new CustomException("关联了套餐不能删除!");
        }
        super.removeById(id);// 这个学学
    }
}
