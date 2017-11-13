package com.yq.dao;

import com.yq.entity.Workorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Mapper
public interface WorkorderMapper {

    void insert(Workorder workorder);

    Workorder select(@Param("workorder") Workorder workorder);

    List<Workorder> selectAll(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("index") Integer index, @Param("size") Integer size);

    int count();

    void delete(Long id);
}
