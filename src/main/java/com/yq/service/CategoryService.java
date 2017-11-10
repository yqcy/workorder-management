package com.yq.service;

import com.yq.dao.CategoryMapper;
import com.yq.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yq.util.DateUtils.currentTime;
import static com.yq.util.PageUtils.compute;

/**
 * created by YQ on 2017-11-08
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Category save(Category category) {
        Date date = currentTime();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        this.categoryMapper.insert(category);
        return category;
    }

    public Category query(Category category) {
        Category result = this.categoryMapper.select(category);
        return result;
    }

    public List<Category> query(Integer pageNum, Integer pageSize) {
        List<Category> result = this.categoryMapper.selectAll(compute(pageNum, pageSize), pageSize);
        return result;
    }

    public Boolean delete(Long id) {
        this.categoryMapper.delete(id);
        return true;
    }
}
