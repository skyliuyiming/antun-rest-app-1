package com.zdjy.bigdata.antun.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class User implements Serializable {
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
     * 数据状态（0：默认状态）
     */
    private Integer status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0：女；1：男）
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 省编号
     */
    private Long province;

    /**
     * 省份名
     */
    private String provinceName;

    /**
     * 城市编号
     */
    private Long city;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 区县编号
     */
    private Long town;

    /**
     * 区县名
     */
    private String townName;

    /**
     * 渠道码
     */
    private String channelCode;

    /**
     * 渠道名
     */
    private String channelName;

    /**
     * 产品码
     */
    private String productCode;

    /**
     * 产品名
     */
    private String productName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getTown() {
        return town;
    }

    public void setTown(Long town) {
        this.town = town;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}