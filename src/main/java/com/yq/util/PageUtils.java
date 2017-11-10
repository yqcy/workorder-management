package com.yq.util;

/**
 * created by YQ on 2017-11-08
 */
public class PageUtils {

    public static Integer compute(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) return null;
        return (pageNum - 1) * pageSize;
    }
}
