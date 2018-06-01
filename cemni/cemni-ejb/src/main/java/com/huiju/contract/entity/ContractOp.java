package com.huiju.contract.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huiju.module.data.BaseEntity;

/**
 * 运营部
 */
@Entity
@Table(name = "D_CONTRACT_OP")
public class ContractOp extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Op_PK")
    @TableGenerator(name = "Contract_Op_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Op_PK", allocationSize = 1)
    private Long operateId;

    private String rent;//房租
    private String waterFree;//水费
    private String electricityFree;//电费
    private String propertFee;//物业费
    private String atend;//保底
    private String atendComAmount;// 保底完成额
    private String atendDifAmount;// 保底差额
    private String debitType;//扣款方式
    private String preciousPoints;//镶嵌扣点
    private String nkPoints;//裸钻扣点
    private String cdPoints;//克拉钻扣点
    private String multPoints;//彩宝扣点
    private String goldPoints;//黄金扣点
    private String kgoldPoints;//K金扣点
    private String emeraldPoints;//翡翠扣点
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountsDate;//结算时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;//回款时间
    private String areaPerson;//区域负责人

    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getWaterFree() {
        return waterFree;
    }

    public void setWaterFree(String waterFree) {
        this.waterFree = waterFree;
    }

    public String getElectricityFree() {
        return electricityFree;
    }

    public void setElectricityFree(String electricityFree) {
        this.electricityFree = electricityFree;
    }

    public String getPropertFee() {
        return propertFee;
    }

    public void setPropertFee(String propertFee) {
        this.propertFee = propertFee;
    }

    public String getAtend() {
        return atend;
    }

    public void setAtend(String atend) {
        this.atend = atend;
    }

    public String getAtendComAmount() {
        return atendComAmount;
    }

    public void setAtendComAmount(String atendComAmount) {
        this.atendComAmount = atendComAmount;
    }

    public String getAtendDifAmount() {
        return atendDifAmount;
    }

    public void setAtendDifAmount(String atendDifAmount) {
        this.atendDifAmount = atendDifAmount;
    }

    public String getDebitType() {
        return debitType;
    }

    public void setDebitType(String debitType) {
        this.debitType = debitType;
    }

    public String getPreciousPoints() {
        return preciousPoints;
    }

    public void setPreciousPoints(String preciousPoints) {
        this.preciousPoints = preciousPoints;
    }

    public String getNkPoints() {
        return nkPoints;
    }

    public void setNkPoints(String nkPoints) {
        this.nkPoints = nkPoints;
    }

    public String getCdPoints() {
        return cdPoints;
    }

    public void setCdPoints(String cdPoints) {
        this.cdPoints = cdPoints;
    }

    public String getMultPoints() {
        return multPoints;
    }

    public void setMultPoints(String multPoints) {
        this.multPoints = multPoints;
    }

    public String getGoldPoints() {
        return goldPoints;
    }

    public void setGoldPoints(String goldPoints) {
        this.goldPoints = goldPoints;
    }

    public String getKgoldPoints() {
        return kgoldPoints;
    }

    public void setKgoldPoints(String kgoldPoints) {
        this.kgoldPoints = kgoldPoints;
    }

    public String getEmeraldPoints() {
        return emeraldPoints;
    }

    public void setEmeraldPoints(String emeraldPoints) {
        this.emeraldPoints = emeraldPoints;
    }

    public Date getAccountsDate() {
        return accountsDate;
    }

    public void setAccountsDate(Date accountsDate) {
        this.accountsDate = accountsDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getAreaPerson() {
        return areaPerson;
    }

    public void setAreaPerson(String areaPerson) {
        this.areaPerson = areaPerson;
    }

}