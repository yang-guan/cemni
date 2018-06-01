package com.huiju.contract.entity;

import javax.persistence.Column;
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
 * 工费金损
 */
@Entity
@Table(name = "D_CONTRACT_FEE")
public class ContractFee extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Fee_PK")
    @TableGenerator(name = "Contract_Fee_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Fee_PK", allocationSize = 1)
    @Column(name = "feeId")
    private Long feeId;

    @Column(name = "type")
    private String type;//品类
    @Column(name = "fee")
    private String fee;//工费
    @Column(name = "goldLoss")
    private String goldLoss;//金损

    @ManyToOne
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;//合同主表关系映射

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getGoldLoss() {
        return goldLoss;
    }

    public void setGoldLoss(String goldLoss) {
        this.goldLoss = goldLoss;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

}