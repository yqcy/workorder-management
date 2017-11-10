package com.yq.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Mapper
public interface WorkorderKeywordRelationMapper {

    void insert(@Param("createTime") Date createTime, @Param("updateTime") Date updateTime, @Param("workorderId") Long workorderId, @Param("keywords") List<Long> keywords);
}
