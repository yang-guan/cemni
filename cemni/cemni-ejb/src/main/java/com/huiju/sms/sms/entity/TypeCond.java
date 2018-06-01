package com.huiju.sms.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.huiju.sms.objcondition.entity.ObjCondition;

/**
 * 短信类型-发送对象的条件的类型-关系
 * 
 * @author：yuhb
 * @date：2016年12月24日 上午11:56:26
 */
@Entity
@Table(name = "D_SMS_TYPE_COND")
public class TypeCond implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long typeCondId;
    private Integer type;
    private String compTable;
    private String compColumn;
    private String compColumnName;
    private Integer compColumVal;

    @OneToMany(mappedBy = "typeCond")
    private List<ObjCondition> objCondtionList;

    public Long getTypeCondId() {
        return typeCondId;
    }

    public void setTypeCondId(Long typeCondId) {
        this.typeCondId = typeCondId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompTable() {
        return compTable;
    }

    public void setCompTable(String compTable) {
        this.compTable = compTable;
    }

    public String getCompColumn() {
        return compColumn;
    }

    public void setCompColumn(String compColumn) {
        this.compColumn = compColumn;
    }

    public String getCompColumnName() {
        return compColumnName;
    }

    public void setCompColumnName(String compColumnName) {
        this.compColumnName = compColumnName;
    }

    public Integer getCompColumVal() {
        return compColumVal;
    }

    public void setCompColumVal(Integer compColumVal) {
        this.compColumVal = compColumVal;
    }

    public List<ObjCondition> getObjCondtionList() {
        return objCondtionList;
    }

    public void setObjCondtionList(List<ObjCondition> objCondtionList) {
        this.objCondtionList = objCondtionList;
    }

}