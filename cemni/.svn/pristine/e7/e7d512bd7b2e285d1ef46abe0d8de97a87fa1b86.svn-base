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
 * 商品部
 */
@Entity
@Table(name = "D_CONTRACT_COM")
public class ContractCom extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Com_PK")
    @TableGenerator(name = "Contract_Com_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Com_PK", allocationSize = 1)
    private Long commodityId;

    private String contact;//联系人
    private String projectName;//合作项目
    private Double coopAmount;//合作金额
    private String coopDate;//合作账期
    private String payDate;//付款日期
    private String paycycle;//付款周期
    private String payType;//付款方式
    private String creditLimit;//信用额度
    private String overRest;//逾期利息
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

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getCoopAmount() {
        return coopAmount;
    }

    public void setCoopAmount(Double coopAmount) {
        this.coopAmount = coopAmount;
    }

    public String getCoopDate() {
        return coopDate;
    }

    public void setCoopDate(String coopDate) {
        this.coopDate = coopDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getOverRest() {
        return overRest;
    }

    public void setOverRest(String overRest) {
        this.overRest = overRest;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPayDate() {
        return payDate;
    }

    public String getPaycycle() {
        return paycycle;
    }

    public void setPaycycle(String paycycle) {
        this.paycycle = paycycle;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

}