package com.yq.service;

import com.yq.dao.WorkorderKeywordRelationMapper;
import com.yq.dao.WorkorderMapper;
import com.yq.entity.Workorder;
import com.yq.util.DateUtils;
import com.yq.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Service
public class WorkorderService {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private PageUtils pageUtils;

    @Autowired
    private WorkorderMapper workorderMapper;

    @Autowired
    private WorkorderKeywordRelationMapper workorderKeywordRelationMapper;

    public Workorder save(Workorder workorder, List<Long> keywords) {
        Date date = this.dateUtils.currentTime();
        workorder.setCreateTime(date);
        workorder.setUpdateTime(date);
        this.workorderMapper.insert(workorder);
        this.workorderKeywordRelationMapper.insert(date, date, workorder.getId(), keywords);
        return workorder;
    }

    public Workorder query(Workorder workorder) {
        return this.workorderMapper.select(workorder);
    }

    public List<Workorder> query(Date beginTime, Date endTime, Integer pageNum, Integer pageSize) {
        return this.workorderMapper.selectAll(beginTime, endTime, this.pageUtils.compute(pageNum, pageSize), pageSize);
    }

    public List<Workorder> query(Integer pageNum, Integer pageSize) {
        return this.query(null, null, pageNum, pageSize);
    }

    public List<Workorder> query(Date begin, Date end) {
        return this.query(begin, end, null, null);
    }

    public void remove(Long id) {
        this.workorderMapper.delete(id);
    }

}
