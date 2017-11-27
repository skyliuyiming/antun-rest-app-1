package com.zdjy.bigdata.antun.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Statistic implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 维度的键
     */
    private String dimensionKey;

    /**
     * 维度的值
     */
    private Long dimensionValue;

    /**
     * 日期
     */
    private String day;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimensionKey() {
        return dimensionKey;
    }

    public void setDimensionKey(String dimensionKey) {
        this.dimensionKey = dimensionKey;
    }

    public Long getDimensionValue() {
        return dimensionValue;
    }

    public void setDimensionValue(Long dimensionValue) {
        this.dimensionValue = dimensionValue;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}