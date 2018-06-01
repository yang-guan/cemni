package com.huiju.salesment.yeartarget.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_SALES_YEAR")
public class YearTarget extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sales_Year_PK")
    @TableGenerator(name = "Sales_Year_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sales_Year_PK", allocationSize = 1)
    private Long yearId;

    private String yearNum;// 指标编码
    private Integer type;// 指标类型
    private Integer partYear;// 指标年份
    private Double allAmount;// 销售总额

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String createUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yearTarget", orphanRemoval = true)
    private List<YearDetails> yearDetails;

    @Transient
    private String typeName;

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPartYear() {
        return partYear;
    }

    public void setPartYear(Integer partYear) {
        this.partYear = partYear;
    }

    public Double getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Double allAmount) {
        this.allAmount = allAmount;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<YearDetails> getYearDetails() {
        return yearDetails;
    }

    public void setYearDetails(List<YearDetails> yearDetails) {
        this.yearDetails = yearDetails;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}