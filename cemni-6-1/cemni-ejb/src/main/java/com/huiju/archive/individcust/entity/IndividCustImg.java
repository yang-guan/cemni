package com.huiju.archive.individcust.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "d_archive_individcust_img")
public class IndividCustImg extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INDIVIDCUST_IMG_PK")
    @TableGenerator(name = "INDIVIDCUST_IMG_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "INDIVIDCUST_IMG_PK", allocationSize = 1)
    private Long imgId;

    private Long individCustId;
    private byte[] image;// 头像

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Long getIndividCustId() {
        return individCustId;
    }

    public void setIndividCustId(Long individCustId) {
        this.individCustId = individCustId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}