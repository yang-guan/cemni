package com.huiju.archive.individcust.entity;

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

@Entity
@Table(name = "D_ARCHIVE_FAMILY")
public class IndividCustFamily extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INDIVIDCUST_FAMILY_PK")
    @TableGenerator(name = "INDIVIDCUST_FAMILY_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INDIVIDCUST_FAMILY_PK", allocationSize = 1)
    private Long familyRelId;

    private String name; // 姓名
    private Integer relationship; // 关系
    @Temporal(TemporalType.DATE)
    private Date birthday; // 出生日期
    private String hobby; // 爱好
    private String tel; // 联系电话
    private String education; // 学历 

    @Temporal(TemporalType.DATE)
    private Date hundredDays; // 百天日

    private Long custody; // 是否有抚养权
    private String kidEducation; // 子女教育
    private String kidHobby; // 子女爱好
    private String remark; // 备注

    @ManyToOne
    @JoinColumn(name = "individCustId", referencedColumnName = "individCustId")
    private IndividCust individCust;

    public Long getFamilyRelId() {
        return familyRelId;
    }

    public void setFamilyRelId(Long familyRelId) {
        this.familyRelId = familyRelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getHundredDays() {
        return hundredDays;
    }

    public void setHundredDays(Date hundredDays) {
        this.hundredDays = hundredDays;
    }

    public Long getCustody() {
        return custody;
    }

    public void setCustody(Long custody) {
        this.custody = custody;
    }

    public String getKidEducation() {
        return kidEducation;
    }

    public void setKidEducation(String kidEducation) {
        this.kidEducation = kidEducation;
    }

    public String getKidHobby() {
        return kidHobby;
    }

    public void setKidHobby(String kidHobby) {
        this.kidHobby = kidHobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public IndividCust getIndividCust() {
        return individCust;
    }

    public void setIndividCust(IndividCust individCust) {
        this.individCust = individCust;
    }

}