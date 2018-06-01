package com.huiju.archive.channel.entity;

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

/**
 * 渠道商信息实体类
 * 
 * @author zzy
 * @date 2016年11月24日 上午11:13:53
 */
@Entity
@Table(name = "D_CHANNEL_INFO")
public class ChannelInfo extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ChannelInfo_PK")
    @TableGenerator(name = "ChannelInfo_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "ChannelInfo_PK", allocationSize = 1)
    private Long channelinfoId;
    /**
     * 地址
     */
    private String address;
    /**
     * 广告位
     */
    private String advertisement;
    /**
     * 坪效
     */
    private String efficient;
    /**
     * 同类品牌销售及盈利
     */
    private String profitability;
    /**
     * 平面路线图等工程信息
     */
    private String engineeringinfo;
    /**
     * 停车位
     */
    private String parking;
    /**
     * 回款及时性
     */
    private String payment;
    /**
     * 春调时间
     */
    @Temporal(TemporalType.DATE)
    private Date sdate;
    /**
     * 秋调时间
     */
    @Temporal(TemporalType.DATE)
    private Date adate;
    /**
     * 调位档案
     */
    private String archives;
    /**
     * 备注
     */
    private String remark;
    @ManyToOne
    @JoinColumn(name = "channelId", referencedColumnName = "channelId")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getChannelinfoId() {
        return channelinfoId;
    }

    public void setChannelinfoId(Long channelinfoId) {
        this.channelinfoId = channelinfoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getEfficient() {
        return efficient;
    }

    public void setEfficient(String efficient) {
        this.efficient = efficient;
    }

    public String getProfitability() {
        return profitability;
    }

    public void setProfitability(String profitability) {
        this.profitability = profitability;
    }

    public String getEngineeringinfo() {
        return engineeringinfo;
    }

    public void setEngineeringinfo(String engineeringinfo) {
        this.engineeringinfo = engineeringinfo;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    public String getArchives() {
        return archives;
    }

    public void setArchives(String archives) {
        this.archives = archives;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}