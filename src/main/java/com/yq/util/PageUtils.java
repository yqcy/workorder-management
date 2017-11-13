package com.yq.util;

import org.springframework.stereotype.Component;

/**
 * created by YQ on 2017-11-08
 */
@Component
public class PageUtils {

    public Integer compute(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) return null;
        return (pageNum - 1) * pageSize;
    }
}
