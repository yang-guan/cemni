package com.huiju.archive.partner.entity;

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

import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.BaseEntity;

/**
 * 异业伙伴管理
 * 
 * @author zzy
 * @date 2016年11月28日 下午4:13:12
 */
@Entity
@Table(name = "D_PARTNER")
public class Partner extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Partner_PK")
    @TableGenerator(name = "Partner_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Partner_PK", allocationSize = 1)
    private Long partnerid;

    private String partnerno;// 编码
    private String partnername;// 名称
    private String name;// 简称
    private String business;// 业务联系人
    private String person;// 实际控制人
    private String telephone;// 联系电话
    private Integer type;// 异业伙伴类型
    private String vatno;// 增值税登记号
    private String licenceno;// 营业执照编号
    private String corporate;// 法人代表
    private String city;// 所在城市
    private String address;// 公司地址

    private String cuser;
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.DATE)
    private Calendar mdate;

    private String creditCode;// 统一社会信用代码
    private String orgNo;// 组织机构代码

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partner", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact;

    @Transient
    private String typeName;

    ////////////////////////////////////////////////////////////////////////////////////////////

    public Long getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(Long partnerid) {
        this.partnerid = partnerid;
    }

    public String getPartnerno() {
        return partnerno;
    }

    public void setPartnerno(String partnerno) {
        this.partnerno = partnerno;
    }

    public String getPartnername() {
        return partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVatno() {
        return vatno;
    }

    public void setVatno(String vatno) {
        this.vatno = vatno;
    }

    public String getLicenceno() {
        return licenceno;
    }

    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}