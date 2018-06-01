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
@Table(name = "D_ARCHIVE_COMPETITOR")
public class CompetitorProduct extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPETITORPRODUCT_PK")
    @TableGenerator(name = "COMPETITORPRODUCT_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "COMPETITORPRODUCT_PK", allocationSize = 1)
    private Long competitorId;

    private String brand;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "groupCustId", referencedColumnName = "groupCustId")
    private GroupCust groupCust;

    public Long getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Long competitorId) {
        this.competitorId = competitorId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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