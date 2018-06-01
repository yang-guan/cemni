package com.huiju.contract.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_CONTRACT")
public class Contract extends BaseEntity<Long> {
    private static final long serialVersionUID = -6775293626899485001L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_PK")
    @TableGenerator(name = "Contract_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_PK", allocationSize = 1)
    @Column(name = "contractId")
    private Long contractId;// 合同管理id
    @Column(name = "contractNum")
    private String contractNum;// 合同编号
    @Column(name = "contractType")
    private Integer contractType;// 合同类型
    @Transient
    private String contractTypeName;// 合同类型名称(只应用于前台展示,不与数据库做关联)
    @Column(name = "contractName")
    private String contractName;// 合同名称
    @Column(name = "partyA")
    private String partyA;// 合同甲方
    @Column(name = "partyB")
    private String partyB;// 合同乙方
    @Column(name = "partyNum")
    private String partyNum;// 乙方编码
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "signDate")
    private Calendar signDate;// 签约日期
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effDate")
    private Calendar effDate;// 生效日期
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "invDate")
    private Calendar invDate;// 失效日期
    @Column(name = "addr")
    private String addr;// 签约地址
    @Column(name = "partyC")
    private String partyC;// 合同乙方
    @Column(name = "remark")
    private String remark;// 备注
    @Column(name = "termsId")
    private String termsId;// 合同条款id
    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;// 创建时间
    @Column(name = "createUser")
    private String createUser;// 创建人
    @Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updateDate;// 更新时间
    @Column(name = "updateUser")
    private String updateUser;// 更新人
    @Column(name = "uploadFileGroupId")
    private String uploadFileGroupId;// 附件组
    @Column(name = "remind")
    private String remind;// 是否到期提醒过
    // 工费金损
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    public List<ContractFee> contractFees;

    // 付款日期
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractPay> contractPays;

    // 行政部
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractAdmin> contractAdmins;
    // 品牌部
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractBrand> contractBrands;
    // 商品部
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractCom> ContractComs;
    // 拓展部
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractExp> ContractExps;
    // 运营部
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract", orphanRemoval = true)
    private List<ContractOp> contractOps;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
        result = prime * result + ((contractName == null) ? 0 : contractName.hashCode());
        result = prime * result + ((contractNum == null) ? 0 : contractNum.hashCode());
        result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contract other = (Contract) obj;
        if (contractId == null) {
            if (other.contractId != null)
                return false;
        } else if (!contractId.equals(other.contractId))
            return false;
        if (contractName == null) {
            if (other.contractName != null)
                return false;
        } else if (!contractName.equals(other.contractName))
            return false;
        if (contractNum == null) {
            if (other.contractNum != null)
                return false;
        } else if (!contractNum.equals(other.contractNum))
            return false;
        if (contractType == null) {
            if (other.contractType != null)
                return false;
        } else if (!contractType.equals(other.contractType))
            return false;
        return true;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getPartyNum() {
        return partyNum;
    }

    public void setPartyNum(String partyNum) {
        this.partyNum = partyNum;
    }

    public Calendar getSignDate() {
        return signDate;
    }

    public void setSignDate(Calendar signDate) {
        this.signDate = signDate;
    }

    public Calendar getEffDate() {
        return effDate;
    }

    public void setEffDate(Calendar effDate) {
        this.effDate = effDate;
    }

    public Calendar getInvDate() {
        return invDate;
    }

    public void setInvDate(Calendar invDate) {
        this.invDate = invDate;
    }

    public List<ContractFee> getContractFees() {
        return contractFees;
    }

    public void setContractFees(List<ContractFee> contractFees) {
        this.contractFees = contractFees;
    }

    public List<ContractPay> getContractPays() {
        return contractPays;
    }

    public void setContractPays(List<ContractPay> contractPays) {
        this.contractPays = contractPays;
    }

    public List<ContractAdmin> getContractAdmins() {
        return contractAdmins;
    }

    public void setContractAdmins(List<ContractAdmin> contractAdmins) {
        this.contractAdmins = contractAdmins;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPartyC() {
        return partyC;
    }

    public void setPartyC(String partyC) {
        this.partyC = partyC;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTermsId() {
        return termsId;
    }

    public void setTermsId(String termsId) {
        this.termsId = termsId;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUploadFileGroupId() {
        return uploadFileGroupId;
    }

    public void setUploadFileGroupId(String uploadFileGroupId) {
        this.uploadFileGroupId = uploadFileGroupId;
    }

    public List<ContractBrand> getContractBrands() {
        return contractBrands;
    }

    public void setContractBrands(List<ContractBrand> contractBrands) {
        this.contractBrands = contractBrands;
    }

    public List<ContractCom> getContractComs() {
        return ContractComs;
    }

    public void setContractComs(List<ContractCom> contractComs) {
        ContractComs = contractComs;
    }

    public List<ContractExp> getContractExps() {
        return ContractExps;
    }

    public void setContractExps(List<ContractExp> contractExps) {
        ContractExps = contractExps;
    }

    public List<ContractOp> getContractOps() {
        return contractOps;
    }

    public void setContractOps(List<ContractOp> contractOps) {
        this.contractOps = contractOps;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

}
