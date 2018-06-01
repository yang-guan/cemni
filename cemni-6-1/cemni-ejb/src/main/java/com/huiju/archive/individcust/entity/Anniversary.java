package com.huiju.archive.individcust.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ARCHIVE_ANNIVERSARY")
public class Anniversary extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ANNIVERSARY_PK")
    @TableGenerator(name = "ANNIVERSARY_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ANNIVERSARY_PK", allocationSize = 1)
    private Long anniversaryId;

    private Integer name; // 姓名
    private String remark;

    @Temporal(TemporalType.DATE)
    private Calendar days;

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    public Long getAnniversaryId() {
        return anniversaryId;
    }

    public void setAnniversaryId(Long anniversaryId) {
        this.anniversaryId = anniversaryId;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Calendar getDays() {
        return days;
    }

    public void setDays(Calendar days) {
        this.days = days;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}