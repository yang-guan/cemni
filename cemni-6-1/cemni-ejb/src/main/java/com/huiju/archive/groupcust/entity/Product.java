package com.huiju.archive.groupcust.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

@Entity
@Table(name = "D_ARCHIVE_PRODUCT")
public class Product extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCT_PK")
    @TableGenerator(name = "PRODUCT_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "PRODUCT_PK", allocationSize = 1)
    private Long productId;

    private String type;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "groupCustId", referencedColumnName = "groupCustId")
    private GroupCust groupCust;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public GroupCust getGroupCust() {
        return groupCust;
    }

    public void setGroupCust(GroupCust groupCust) {
        this.groupCust = groupCust;
    }

}