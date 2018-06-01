package com.huiju.inter.saleorder.entity;

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

@Entity
@Table(name = "D_SALE_ORDER")
public class SaleOrder extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SaleOrder_PK")
    @TableGenerator(name = "SaleOrder_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "SaleOrder_PK", allocationSize = 1)
    private Long orderId;

    private Integer type;// number
    @Transient
	private String typeName;
    private String orderNo;// varchar2(20)     订单号

    @Temporal(TemporalType.DATE)
    private Calendar orderDate;// date         订单日期

    private String fraCode;// varchar2(20)     加盟商编号
    private String fraName;// nvarchar2(50)    加盟商名称
    private String storeNo;// varchar2(20)     门店编号
    private String storeName;// nvarchar2(50)  门店名称
    private String busiFlow;// nvarchar2(50)   业务流程号
    private Double totalAmount;// number(8)    销售金额

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getFraCode() {
        return fraCode;
    }

    public void setFraCode(String fraCode) {
        this.fraCode = fraCode;
    }

    public String getFraName() {
        return fraName;
    }

    public void setFraName(String fraName) {
        this.fraName = fraName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBusiFlow() {
        return busiFlow;
    }

    public void setBusiFlow(String busiFlow) {
        this.busiFlow = busiFlow;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}