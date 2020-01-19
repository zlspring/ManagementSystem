package com.aohui.btcorg.domain;

public class QueryUsersReq {
    private String queryKeyword;
    private Boolean verified;
    private Integer pageNum;
    private Integer pageSize;

    public String getQueryKeyword() {
        return queryKeyword;
    }

    public void setQueryKeyword(String queryKeyword) {
        this.queryKeyword = queryKeyword;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
