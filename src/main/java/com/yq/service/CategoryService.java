package com.yq.service;

import com.yq.dao.CategoryMapper;
import com.yq.entity.Category;
import com.yq.util.DateUtils;
import com.yq.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * created by YQ on 2017-11-08
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private PageUtils pageUtils;

    @Autowired
    private CategoryMapper categoryMapper;

    public Category save(Category category) {
        Date date = this.dateUtils.currentTime();
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
        List<Category> result = this.categoryMapper.selectAll(this.pageUtils.compute(pageNum, pageSize), pageSize);
        return result;
    }

    public void remove(Long id) {
        this.categoryMapper.delete(id);
    }
}
