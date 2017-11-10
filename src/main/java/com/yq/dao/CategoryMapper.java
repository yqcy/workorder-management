package com.yq.dao;

import com.yq.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by YQ on 2017-11-06
 */
@Mapper
public interface CategoryMapper {
    void insert(Category category);

    Category select(Category category);

    List<Category> selectAll(@Param("index") Integer index, @Param("size") Integer size);

    int count();

    void delete(Long id);
}
