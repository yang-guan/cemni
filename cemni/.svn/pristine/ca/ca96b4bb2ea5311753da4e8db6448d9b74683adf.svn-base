package com.huiju.expandbusi.individcompanalyze.entity;

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
@Table(name = "D_INDIVID_DETAIL")
public class Indicators extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INDICATORS_PK")
    @TableGenerator(name = "INDICATORS_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INDICATORS_PK", allocationSize = 1)
    private Long indicatorsId;

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

    private String worknumber; // 工号
    private String workName; // 姓名
    private String position; // 职位
    private String levelr; // 岗位级别
    private Double baseRadix; // 基数
    private Double moneyAmount; // 金额

    @ManyToOne
    @JoinColumn(name = "individCompanalyzeId", referencedColumnName = "individCompanalyzeId")
    private IndividCompAnalyze individCompAnalyze;

    public Long getIndicatorsId() {
        return indicatorsId;
    }

    public void setIndicatorsId(Long indicatorsId) {
        this.indicatorsId = indicatorsId;
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

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevelr() {
        return levelr;
    }

    public void setLevelr(String levelr) {
        this.levelr = levelr;
    }

    public Double getBaseRadix() {
        return baseRadix;
    }

    public void setBaseRadix(Double baseRadix) {
        this.baseRadix = baseRadix;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public IndividCompAnalyze getIndividCompAnalyze() {
        return individCompAnalyze;
    }

    public void setIndividCompAnalyze(IndividCompAnalyze individCompAnalyze) {
        this.individCompAnalyze = individCompAnalyze;
    }

}