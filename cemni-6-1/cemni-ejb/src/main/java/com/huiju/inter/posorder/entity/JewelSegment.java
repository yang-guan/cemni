package com.huiju.inter.posorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_JEWEL_SEGMENT")
public class JewelSegment extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    private String segmentName;
    private Double jewelWeight;
    private Integer dictVal;// 字典表9801

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public Double getJewelWeight() {
        return jewelWeight;
    }

    public void setJewelWeight(Double jewelWeight) {
        this.jewelWeight = jewelWeight;
    }

    public Integer getDictVal() {
        return dictVal;
    }

    public void setDictVal(Integer dictVal) {
        this.dictVal = dictVal;
    }

}