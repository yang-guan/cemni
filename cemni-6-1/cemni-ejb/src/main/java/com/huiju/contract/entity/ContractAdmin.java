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
 * 行政部
 */
@Entity
@Table(name = "D_CONTRACT_ADMIN")
public class ContractAdmin extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Admin_PK")
    @TableGenerator(name = "Contract_Admin_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Admin_PK", allocationSize = 1)
    private Long adminId;

    private String contractNatrue;//合同性质
    private String amount;//合同金额
    private String payType;//付款方式
    private String contacts;//联系人
    private String purProject;//采购项目
    private String trainc;//采购课程
    private String advClass;//顾问类别
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getContractNatrue() {
        return contractNatrue;
    }

    public void setContractNatrue(String contractNatrue) {
        this.contractNatrue = contractNatrue;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPurProject() {
        return purProject;
    }

    public void setPurProject(String purProject) {
        this.purProject = purProject;
    }

    public String getTrainc() {
        return trainc;
    }

    public void setTrainc(String trainc) {
        this.trainc = trainc;
    }

    public String getAdvClass() {
        return advClass;
    }

    public void setAdvClass(String advClass) {
        this.advClass = advClass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}