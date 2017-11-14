package com.zdjy.bigdata.antun.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class UserSending implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 创建记录
     */
    private Date gmtCreate;

    /**
     * 修改记录
     */
    private Date gmtModified;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 返回码
     */
    private Integer responseCode;

    /**
     * 返回信息
     */
    private String responseMessage;

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}