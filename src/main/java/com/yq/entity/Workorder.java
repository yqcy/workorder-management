package com.yq.entity;

import java.util.List;

/**
 * created by YQ on 2017-11-06
 */
public class Workorder extends BaseEntity {
    private String number;//工单号
    private Category category;//工单的类型
    private List<Keyword> keywords;//关键词

    public Workorder() {
    }

    public Workorder(String number) {
        this.number = number;
    }

    public Workorder(Long id, String number) {
        super.id = id;
        this.number = number;
    }

    public Workorder(String number, Long categoryId) {
        this.number = number;
        this.category = new Category(categoryId);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
