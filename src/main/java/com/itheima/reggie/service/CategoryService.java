package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;
import org.springframework.stereotype.Service;

/**
 * @author huanghua 2023/7/5
 */
public interface CategoryService extends IService<Category> {
     void removeBeforeJudge(Long id);
}
