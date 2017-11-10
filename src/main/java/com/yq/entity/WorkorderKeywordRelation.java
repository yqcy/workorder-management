package com.yq.entity;

/**
 * created by wb-yq264139 on 2017/11/10
 */
public class WorkorderKeywordRelation extends BaseEntity {
    private Long workorderId;

    private Long keywordId;

    public Long getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(Long workorderId) {
        this.workorderId = workorderId;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }
}
