package com.yq.entity;

/**
 * created by YQ on 2017-11-06
 */
public class Category extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        super.id = id;
        this.name = name;
    }
}
