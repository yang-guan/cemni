package com.huiju.archive.channel.entity;

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
import javax.persistence.Transient;

import com.huiju.archive.franchisee.entity.Contact;
import com.huiju.module.data.BaseEntity;

/**
 * 渠道商管理实体类
 * 
 * @author zzy
 * @date 2016年11月22日 上午10:02:03
 */
@Entity
@Table(name = "D_CHANNEL")
public class Channel extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Channel_PK")
    @TableGenerator(name = "Channel_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Channel_PK", allocationSize = 1)
    private Long channelId;

    private String channelno;// 渠道商编码
    private String channelname;// 渠道商名称
    private String name;// 简称
    private Integer type;// 类型
    private String person;// 实际控制人
    private String vatno;// 增值税号登记
    private String licenceno;// 营业执照编号
    private String corporate;// 法人代表
    private String city;// 所在城市
    private String address;// 公司地址
    private String business;// 业务联系人
    private String telephone;// 联系电话
    private Integer isValid;// 是否有效
    private String creditCode;// 统一社会信用代码
    private String orgNo;// 组织机构代码

	private String cuser;
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    private String muser;
    @Temporal(TemporalType.DATE)
    private Calendar mdate;

    private String uploadFileGroupId; // 附件

    @Transient
    private String typeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChannelInfo> channelinfo;

    @OneToMany(mappedBy = "channel")
    private List<Contact> contact;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Long getChannelId() {
        return channelId;
    }

    public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelno() {
        return channelno;
    }

    public void setChannelno(String channelno) {
        this.channelno = channelno;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getVatno() {
        return vatno;
    }

    public void setVatno(String vatno) {
        this.vatno = vatno;
    }

    public String getLicenceno() {
        return licenceno;
    }

    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public Calendar getCdate() {
        return cdate;
    }

    public void setCdate(Calendar cdate) {
        this.cdate = cdate;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public Calendar getMdate() {
        return mdate;
    }

    public void setMdate(Calendar mdate) {
        this.mdate = mdate;
    }

    public String getUploadFileGroupId() {
        return uploadFileGroupId;
    }

    public void setUploadFileGroupId(String uploadFileGroupId) {
        this.uploadFileGroupId = uploadFileGroupId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ChannelInfo> getChannelinfo() {
        return channelinfo;
    }

    public void setChannelinfo(List<ChannelInfo> channelinfo) {
        this.channelinfo = channelinfo;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

}