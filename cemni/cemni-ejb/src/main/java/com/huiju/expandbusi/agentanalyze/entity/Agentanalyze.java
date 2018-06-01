package com.huiju.expandbusi.agentanalyze.entity;

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
 * 加盟商/区域代理业务分解
 * 
 * @author zzy
 * @date 2016年11月30日 上午9:49:07
 */
@Entity
@Table(name = "D_AGENTANALYZE")
public class Agentanalyze extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Agentanalyze_PK")
    @TableGenerator(name = "Agentanalyze_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Agentanalyze_PK", allocationSize = 1)
    private Long agentanalyzeid;

    private String agentanalyzeno;// 指标单号
    private Integer type;// 指标类别
    private Integer year;// 年份

    private String cuser;
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.DATE)
    private Calendar mdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentanalyze", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Detailed> detailed;

    @Transient
    private String typeName;

    public Long getAgentanalyzeid() {
        return agentanalyzeid;
    }

    public void setAgentanalyzeid(Long agentanalyzeid) {
        this.agentanalyzeid = agentanalyzeid;
    }

    public String getAgentanalyzeno() {
        return agentanalyzeno;
    }

    public void setAgentanalyzeno(String agentanalyzeno) {
        this.agentanalyzeno = agentanalyzeno;
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

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public List<Detailed> getDetailed() {
        return detailed;
    }

    public void setDetailed(List<Detailed> detailed) {
        this.detailed = detailed;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}