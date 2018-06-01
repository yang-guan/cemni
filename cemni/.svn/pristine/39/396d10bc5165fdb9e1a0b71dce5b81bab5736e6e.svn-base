package com.huiju.salesment.yeartarget.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_SALES_YEDETAIL")
public class YearDetails extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sales_Yedetail_PK")
    @TableGenerator(name = "Sales_Yedetail_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sales_Yedetail_PK", allocationSize = 1)
    private Long yearDetailsId;

    @ManyToOne
    @JoinColumn(name = "yearId", referencedColumnName = "yearId")
    private YearTarget yearTarget;

    // 需要存储老数据
    // 大区
    private Long bigAreaId;
    private String bigAreaNo;
    private String bigAreaName;
    // 区域
    private Long areaId;
    private String areaNo;
    private String areaName;
    // 门店
    private Long storeId;
    private String storeNo;
    private String name;
    private Integer attr;
    @Transient
    private String attrName;

    private Double jan;
    private Double feb;
    private Double mar;
    private Double apr;
    private Double may;
    private Double jun;
    private Double jul;
    private Double aug;
    private Double sep;
    private Double oct;
    private Double nov;
    private Double dec;

    public Long getYearDetailsId() {
        return yearDetailsId;
    }

    public void setYearDetailsId(Long yearDetailsId) {
        this.yearDetailsId = yearDetailsId;
    }

    public YearTarget getYearTarget() {
        return yearTarget;
    }

    public void setYearTarget(YearTarget yearTarget) {
        this.yearTarget = yearTarget;
    }

    public Long getBigAreaId() {
        return bigAreaId;
    }

    public void setBigAreaId(Long bigAreaId) {
        this.bigAreaId = bigAreaId;
    }

    public String getBigAreaNo() {
        return bigAreaNo;
    }

    public void setBigAreaNo(String bigAreaNo) {
        this.bigAreaNo = bigAreaNo;
    }

    public String getBigAreaName() {
        return bigAreaName;
    }

    public void setBigAreaName(String bigAreaName) {
        this.bigAreaName = bigAreaName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttr() {
        return attr;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Double getJan() {
        return jan;
    }

    public void setJan(Double jan) {
        this.jan = jan;
    }

    public Double getFeb() {
        return feb;
    }

    public void setFeb(Double feb) {
        this.feb = feb;
    }

    public Double getMar() {
        return mar;
    }

    public void setMar(Double mar) {
        this.mar = mar;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJun() {
        return jun;
    }

    public void setJun(Double jun) {
        this.jun = jun;
    }

    public Double getJul() {
        return jul;
    }

    public void setJul(Double jul) {
        this.jul = jul;
    }

    public Double getAug() {
        return aug;
    }

    public void setAug(Double aug) {
        this.aug = aug;
    }

    public Double getSep() {
        return sep;
    }

    public void setSep(Double sep) {
        this.sep = sep;
    }

    public Double getOct() {
        return oct;
    }

    public void setOct(Double oct) {
        this.oct = oct;
    }

    public Double getNov() {
        return nov;
    }

    public void setNov(Double nov) {
        this.nov = nov;
    }

    public Double getDec() {
        return dec;
    }

    public void setDec(Double dec) {
        this.dec = dec;
    }

}