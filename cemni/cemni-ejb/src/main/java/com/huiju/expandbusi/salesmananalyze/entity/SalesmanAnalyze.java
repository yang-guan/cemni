package com.huiju.expandbusi.salesmananalyze.entity;

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

/**
 * 业务员业绩分解
 * 
 * @author zzy
 * @date 2016年12月27日 下午4:48:31
 */
@Entity
@Table(name = "D_SALESMANANALYZE")
public class SalesmanAnalyze extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SALESMANANALYZE_PK")
    @TableGenerator(name = "SALESMANANALYZE_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "SALESMANANALYZE_PK", allocationSize = 1)
    private Long salesmananalyzeId;

    private String salesmananalyzeNo;// 指标单号
    private Integer year;// 年份

    private String cuser;
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.DATE)
    private Calendar mdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesmananalyze", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Storedetail> storedetail;

    public Long getSalesmananalyzeId() {
        return salesmananalyzeId;
    }

    public void setSalesmananalyzeId(Long salesmananalyzeId) {
        this.salesmananalyzeId = salesmananalyzeId;
    }

    public String getSalesmananalyzeNo() {
        return salesmananalyzeNo;
    }

    public void setSalesmananalyzeNo(String salesmananalyzeNo) {
        this.salesmananalyzeNo = salesmananalyzeNo;
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

    public List<Storedetail> getStoredetail() {
        return storedetail;
    }

    public void setStoredetail(List<Storedetail> storedetail) {
        this.storedetail = storedetail;
    }

}