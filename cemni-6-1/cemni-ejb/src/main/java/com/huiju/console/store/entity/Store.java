package com.huiju.console.store.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.archive.franchisee.entity.Franchisee;
import com.huiju.console.org.entity.Org;
import com.huiju.console.orgext.entity.OrgExt;
import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_CN_STORE")
public class Store extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Cn_Store_PK")
    @TableGenerator(name = "Cn_Store_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Cn_Store_PK", allocationSize = 1)
    private Long storeId;

    private String storeNo;// varchar2(20) 门店编码
    private String name;// varchar2(100)   门店名称
    private Integer attr;// number(4)      门店属性：1加盟店、2直营店
    private Integer form;// number(4)      门店形态：1商场、2专卖店、3联营店

    @Temporal(TemporalType.DATE)
    private Calendar shopupDate;// date    开店日期

    private String tel;// varchar2(20)     门店联系电话
    private Integer isValid;// number(4)   是否有效
    private Integer province;// number(6)  省
    private String provinceName;
    private Integer city;// number(6)      市
    private String cityName;
    private Integer county;// number(6)    县/区
    private String countyName;
    private String addr;// varchar2(100)   地址

    // 大区（接口同步数据，在crm端生成id）
    private Long bigAreaId;
    private String bigAreaNo;
    private String bigAreaName;
    // 区域（接口同步数据，在crm端生成id）
    private Long areaId;
    private String areaNo;
    private String areaName;

    @ManyToOne
    @JoinColumn(name = "franchiseeId", referencedColumnName = "franchiseeId")
    private Franchisee franchisee;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "store")
    private Org org;

    @OneToOne(mappedBy = "store")
    private OrgExt orgExt;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar mdate;

    @Transient
    private String attrName;
    @Transient
    private String formName;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Calendar getShopupDate() {
        return shopupDate;
    }

    public void setShopupDate(Calendar shopupDate) {
        this.shopupDate = shopupDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public Franchisee getFranchisee() {
        return franchisee;
    }

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public OrgExt getOrgExt() {
        return orgExt;
    }

    public void setOrgExt(OrgExt orgExt) {
        this.orgExt = orgExt;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

}