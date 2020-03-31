package com.hebei.core.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    //第几页
    @ApiModelProperty(value = "起始页", required = false)
    private int page = 1;
    //每页行数
    @ApiModelProperty(value = "每页显示数量,0为不分页", required = false)
    private int rows = 10;
    @ApiModelProperty(value = "排序列", required = false)
    private String orderField;
    @ApiModelProperty(value = "排序方式", required = false)
    private String orderSeq;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    @Override
    public String toString() {
        return "PageBean [page=" + page + ", rows=" + rows + "]";
    }


}
