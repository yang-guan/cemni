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
 * 拓展部
 */
@Entity
@Table(name = "D_CONTRACT_EXP")
public class ContractExp extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Exp_PK")
    @TableGenerator(name = "Contract_Exp_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Exp_PK", allocationSize = 1)
    private Long expandId;

    private String storeArea;//门店面积
    private String rent;//租金
    private String points;//扣点
    private String franChisee;//加盟商
    private String storeYears;//门店年限
    private String firstBatch;//首批
    private String replenishA;//门店补货
    private String contractBodyA;//区域合同主体
    private String areaAgent;//区域代理
    private String agentYears;//区域年限
    private String replenishB;//区域补货
    private String openStore;//开店
    private String contractBodyB;//代理合同主体
    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getExpandId() {
        return expandId;
    }

    public void setExpandId(Long expandId) {
        this.expandId = expandId;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getFranChisee() {
        return franChisee;
    }

    public void setFranChisee(String franChisee) {
        this.franChisee = franChisee;
    }

    public String getStoreYears() {
        return storeYears;
    }

    public void setStoreYears(String storeYears) {
        this.storeYears = storeYears;
    }

    public String getFirstBatch() {
        return firstBatch;
    }

    public void setFirstBatch(String firstBatch) {
        this.firstBatch = firstBatch;
    }

    public String getAreaAgent() {
        return areaAgent;
    }

    public void setAreaAgent(String areaAgent) {
        this.areaAgent = areaAgent;
    }

    public String getAgentYears() {
        return agentYears;
    }

    public void setAgentYears(String agentYears) {
        this.agentYears = agentYears;
    }

    public String getReplenishA() {
        return replenishA;
    }

    public void setReplenishA(String replenishA) {
        this.replenishA = replenishA;
    }

    public String getReplenishB() {
        return replenishB;
    }

    public void setReplenishB(String replenishB) {
        this.replenishB = replenishB;
    }

    public String getOpenStore() {
        return openStore;
    }

    public void setOpenStore(String openStore) {
        this.openStore = openStore;
    }

    public String getContractBodyA() {
        return contractBodyA;
    }

    public void setContractBodyA(String contractBodyA) {
        this.contractBodyA = contractBodyA;
    }

    public String getContractBodyB() {
        return contractBodyB;
    }

    public void setContractBodyB(String contractBodyB) {
        this.contractBodyB = contractBodyB;
    }

}