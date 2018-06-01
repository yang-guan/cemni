package com.huiju.expandbusi.memcompanalyze.entity;

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
 * 会员完成指标
 */
@Entity
@Table(name = "D_MEMCOMPANALYZE")
public class MemCompAnalyze extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMCOMPANALYZE_PK")
    @TableGenerator(name = "MEMCOMPANALYZE_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "MEMCOMPANALYZE_PK", allocationSize = 1)
    private Long memcompanalyzeId;

    private String memcompanalyzenNo;
    private Integer type;
    private Integer year;

    private String cuser;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;

    @Transient
    private String typeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memCompAnalyze", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Memdetail> memdetailist;

    public Long getMemcompanalyzeId() {
        return memcompanalyzeId;
    }

    public void setMemcompanalyzeId(Long memcompanalyzeId) {
        this.memcompanalyzeId = memcompanalyzeId;
    }

    public String getMemcompanalyzenNo() {
        return memcompanalyzenNo;
    }

    public void setMemcompanalyzenNo(String memcompanalyzenNo) {
        this.memcompanalyzenNo = memcompanalyzenNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Memdetail> getMemdetailist() {
        return memdetailist;
    }

    public void setMemdetailist(List<Memdetail> memdetailist) {
        this.memdetailist = memdetailist;
    }

}