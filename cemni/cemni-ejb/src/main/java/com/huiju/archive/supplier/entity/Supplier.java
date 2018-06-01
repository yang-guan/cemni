package com.huiju.archive.supplier.entity;

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
 * 供应商
 * 
 * @author zzy
 * @date 2016年12月15日 下午5:43:13
 */
@Entity
@Table(name = "D_SUPPLIER")
public class Supplier extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Supplier_PK")
    @TableGenerator(name = "Supplier_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Supplier_PK", allocationSize = 1)
    private Long supplierid;

    private String supplierno;
    private String suppliername;
    private String name;
    private String person;// 实际控制人
    private String amountscope;// 注册主体范围名称
    private String invoicename;// 开票全称
    private String taxpayercode;// 纳税人识别码
    private String business;// 业务联系人
    private String telephone;// 联系电话
    private String faxnum;// 传真号码
    private String address;// 公司地址
    private String bank;// 开户行
    private String account;// 开户账户
    private String project;// 合作项目
    private String accounttime;// 合作账期
    private Integer gradeassess;// 年度等级评估
    private String target;// 次年预计合作目标
    private String fee;// 工费
    private String goidloss;// 金损
    private String errorrate;// 产品出错率
    private String remark;// 备注
    private Integer isValid;// 是否有效
    private String creditCode;// 统一社会信用代码
    private String orgNo;// 组织机构代码

    private String cuser;
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.DATE)
    private Calendar mdate;

    @Transient
    private String gradeassessname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public String getSupplierno() {
        return supplierno;
    }

    public Long getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public void setSupplierno(String supplierno) {
        this.supplierno = supplierno;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAmountscope() {
        return amountscope;
    }

    public void setAmountscope(String amountscope) {
        this.amountscope = amountscope;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    public String getTaxpayercode() {
        return taxpayercode;
    }

    public void setTaxpayercode(String taxpayercode) {
        this.taxpayercode = taxpayercode;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFaxnum() {
        return faxnum;
    }

    public void setFaxnum(String faxnum) {
        this.faxnum = faxnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAccounttime() {
        return accounttime;
    }

    public void setAccounttime(String accounttime) {
        this.accounttime = accounttime;
    }

    public Integer getGradeassess() {
        return gradeassess;
    }

    public void setGradeassess(Integer gradeassess) {
        this.gradeassess = gradeassess;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getGoidloss() {
        return goidloss;
    }

    public void setGoidloss(String goidloss) {
        this.goidloss = goidloss;
    }

    public String getErrorrate() {
        return errorrate;
    }

    public void setErrorrate(String errorrate) {
        this.errorrate = errorrate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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

    public String getGradeassessname() {
        return gradeassessname;
    }

    public void setGradeassessname(String gradeassessname) {
        this.gradeassessname = gradeassessname;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

}