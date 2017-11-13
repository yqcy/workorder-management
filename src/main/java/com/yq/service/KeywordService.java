package com.yq.service;

import com.yq.dao.KeywordMapper;
import com.yq.entity.Keyword;
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
public class KeywordService {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private PageUtils pageUtils;

    @Autowired
    private KeywordMapper keywordMapper;

    public Keyword save(Keyword keyword) {
        Date date = this.dateUtils.currentTime();
        keyword.setCreateTime(date);
        keyword.setUpdateTime(date);
        this.keywordMapper.insert(keyword);
        return keyword;
    }

    public Keyword query(Keyword keyword) {
        Keyword result = this.keywordMapper.select(keyword);
        return result;
    }

    public List<Keyword> query(Integer pageNum, Integer pageSize) {
        return this.keywordMapper.selectAll(this.pageUtils.compute(pageNum, pageSize), pageSize);
    }

    public void remove(Long id) {
        this.keywordMapper.delete(id);
    }

}
