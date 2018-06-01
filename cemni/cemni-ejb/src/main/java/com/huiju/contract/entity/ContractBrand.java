package com.huiju.contract.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

/**
 * 品牌部
 */
@Entity
@Table(name = "D_CONTRACT_BRAND")
public class ContractBrand extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Brand_PK")
    @TableGenerator(name = "Contract_Brand_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Brand_PK", allocationSize = 1)
    private Long brandId;

    private String contactsA;//甲方联系人
    private String contactsB;//乙方联系
    private String contactsC;//丙方联系人
    private String phoneA;//甲方联系人电话
    private String phoneB;//乙方联系人电话
    private String phoneC;//丙方联系人电话
    private String amount;//合同金额
    private String project;//合作项目
    private String remark;//备注

    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getContactsA() {
        return contactsA;
    }

    public void setContactsA(String contactsA) {
        this.contactsA = contactsA;
    }

    public String getContactsB() {
        return contactsB;
    }

    public void setContactsB(String contactsB) {
        this.contactsB = contactsB;
    }

    public String getContactsC() {
        return contactsC;
    }

    public void setContactsC(String contactsC) {
        this.contactsC = contactsC;
    }

    public String getPhoneA() {
        return phoneA;
    }

    public void setPhoneA(String phoneA) {
        this.phoneA = phoneA;
    }

    public String getPhoneB() {
        return phoneB;
    }

    public void setPhoneB(String phoneB) {
        this.phoneB = phoneB;
    }

    public String getPhoneC() {
        return phoneC;
    }

    public void setPhoneC(String phoneC) {
        this.phoneC = phoneC;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}