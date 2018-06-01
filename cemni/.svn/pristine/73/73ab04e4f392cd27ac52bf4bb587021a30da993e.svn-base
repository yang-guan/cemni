package com.huiju.salesment.designertarget.entity;

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

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_SALES_DESIGN")
public class DesignerTarget extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sales_Design_PK")
    @TableGenerator(name = "Sales_Design_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sales_Design_PK", allocationSize = 1)
    private Long designId;

    private String desNum;// 指标编码
    private Integer partYear;// 年份

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    private String createUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "designerTarget", orphanRemoval = true)
    private List<DesignerDetails> designerDetails;

    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    public String getDesNum() {
        return desNum;
    }

    public void setDesNum(String desNum) {
        this.desNum = desNum;
    }

    public Integer getPartYear() {
        return partYear;
    }

    public void setPartYear(Integer partYear) {
        this.partYear = partYear;
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

    public List<DesignerDetails> getDesignerDetails() {
        return designerDetails;
    }

    public void setDesignerDetails(List<DesignerDetails> designerDetails) {
        this.designerDetails = designerDetails;
    }

}