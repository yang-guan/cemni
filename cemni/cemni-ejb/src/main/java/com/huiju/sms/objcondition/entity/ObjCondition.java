package com.huiju.sms.objcondition.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.huiju.module.data.BaseEntity;
import com.huiju.sms.sms.entity.Sms;
import com.huiju.sms.sms.entity.TypeCond;

/**
 * 发送条件
 * 
 * @author：yuhb
 * @date：2016年12月31日 下午1:10:46
 */
@Entity
@Table(name = "D_SMS_OBJ_CONDITION")
public class ObjCondition extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ObjCondition_PK")
    @TableGenerator(name = "ObjCondition_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ObjCondition_PK", allocationSize = 1)
    private String objConditionId;

    @ManyToOne
    @JoinColumn(name = "smsId", referencedColumnName = "smsId")
    private Sms sms;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "typeCondId", referencedColumnName = "typeCondId")
    private TypeCond typeCond;

    private String compSymbol;// varchar2(10) 条件符号
    private Integer compVal;// varchar2(100)  比较值

    @Transient
    private String compValName;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getObjConditionId() {
        return objConditionId;
    }

    public void setObjConditionId(String objConditionId) {
        this.objConditionId = objConditionId;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    public TypeCond getTypeCond() {
        return typeCond;
    }

    public void setTypeCond(TypeCond typeCond) {
        this.typeCond = typeCond;
    }

    public String getCompSymbol() {
        return compSymbol;
    }

    public void setCompSymbol(String compSymbol) {
        this.compSymbol = compSymbol;
    }

    public Integer getCompVal() {
        return compVal;
    }

    public void setCompVal(Integer compVal) {
        this.compVal = compVal;
    }

    public String getCompValName() {
        return compValName;
    }

    public void setCompValName(String compValName) {
        this.compValName = compValName;
    }

}