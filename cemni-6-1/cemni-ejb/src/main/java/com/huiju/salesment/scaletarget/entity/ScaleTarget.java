package com.huiju.salesment.scaletarget.entity;

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

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_SALES_SCALE")
public class ScaleTarget extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sales_Scale_PK")
    @TableGenerator(name = "Sales_Scale_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sales_Scale_PK", allocationSize = 1)
    private Long scaleId;

    private String scaleNum;// 指标编码
    private Integer partYear;// 年份

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String createUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scaleTarget", orphanRemoval = true)
    private List<ScaleDetails> scaleDetails;

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public String getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(String scaleNum) {
        this.scaleNum = scaleNum;
    }

    public Integer getPartYear() {
        return partYear;
    }

    public void setPartYear(Integer partYear) {
        this.partYear = partYear;
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

    public List<ScaleDetails> getScaleDetails() {
        return scaleDetails;
    }

    public void setScaleDetails(List<ScaleDetails> scaleDetails) {
        this.scaleDetails = scaleDetails;
    }

}