package com.huiju.contract.terms.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;

/**
 * 合同条款模板
 */
@Entity
@Table(name = "d_contract_terms")
public class ContractTerms extends BaseEntity<Long> {
    private static final long serialVersionUID = 6642262328135400823L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Contract_Terms_PK")
    @TableGenerator(name = "Contract_Terms_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Contract_Terms_PK", allocationSize = 1)
    private Long termsId;

    private String termsNum;//条款编码
    private String termsName;//条款名称
    private String content;//条款内容
    private Integer termsType;//条款类型（与合同管理中心合同类型对应关系，不同条款类型属于不同合同类型）
    @Transient
    private String termsTypeName;
    private String natrue;//合同性质
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;//创建时间
    private String createUser;//创建人
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updateDate;//最后修改时间
    private String updateUser;//最后修改人
    private String remark;//备注

    public Long getTermsId() {
        return termsId;
    }

    public void setTermsId(Long termsId) {
        this.termsId = termsId;
    }

    public String getTermsName() {
        return termsName;
    }

    public void setTermsName(String termsName) {
        this.termsName = termsName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTermsType() {
        return termsType;
    }

    public void setTermsType(Integer termsType) {
        this.termsType = termsType;
    }

    public String getTermsTypeName() {
        return termsTypeName;
    }

    public void setTermsTypeName(String termsTypeName) {
        this.termsTypeName = termsTypeName;
    }

    public String getNatrue() {
        return natrue;
    }

    public void setNatrue(String natrue) {
        this.natrue = natrue;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTermsNum() {
        return termsNum;
    }

    public void setTermsNum(String termsNum) {
        this.termsNum = termsNum;
    }

}