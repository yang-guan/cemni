package com.huiju.expandbusi.individcompanalyze.entity;

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

/**
 * 个人业绩完成指标分解
 */
@Entity
@Table(name = "D_INDIVIDCOMPANALYZE")
public class IndividCompAnalyze extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INDIVIDCOMPANALYZE_PK")
    @TableGenerator(name = "INDIVIDCOMPANALYZE_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INDIVIDCOMPANALYZE_PK", allocationSize = 1)
    private Long individCompanalyzeId;

    private String individCompanalyzeNo;
    private Integer year;
    private Integer month;

    private String cuser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "individCompAnalyze", orphanRemoval = true)
    private List<Indicators> indicators;

    @Transient
    private Integer excelFlag; // 是否是excel导入：1是、0否

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getIndividCompanalyzeId() {
        return individCompanalyzeId;
    }

    public void setIndividCompanalyzeId(Long individCompanalyzeId) {
        this.individCompanalyzeId = individCompanalyzeId;
    }

    public String getIndividCompanalyzeNo() {
        return individCompanalyzeNo;
    }

    public void setIndividCompanalyzeNo(String individCompanalyzeNo) {
        this.individCompanalyzeNo = individCompanalyzeNo;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public List<Indicators> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicators> indicators) {
        this.indicators = indicators;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getExcelFlag() {
        return excelFlag;
    }

    public void setExcelFlag(Integer excelFlag) {
        this.excelFlag = excelFlag;
    }

}