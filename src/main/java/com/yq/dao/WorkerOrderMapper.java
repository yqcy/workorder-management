package com.yq.dao;

import com.yq.entity.Keyword;
import com.yq.entity.Workorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Mapper
public interface WorkerOrderMapper {

    void insert(Workorder workorder);

    Workorder select(Workorder keyword);

    List<Keyword> selectAll(@Param("index") Integer index, @Param("size") Integer size);

    int count();

    void delete(Long id);
}
