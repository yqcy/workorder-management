package com.yq.entity;

/**
 * created by YQ on 2017-11-06
 */
public class Keyword extends BaseEntity {
    private String name;

    public Keyword() {
    }

    public Keyword(String name) {
        this.name = name;
    }

    public Keyword(Long id, String name) {
        super.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
