package com.huiju.competitor.entity;

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

import com.huiju.module.data.BaseEntity;

/**
 * 竞争对手公司信息表
 */

@Entity(name = "Competitor")
@Table(name = "D_COMPETITOR_MANAGE")
public class Competitor extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Competitor_PK")
    @TableGenerator(name = "Competitor_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Competitor_PK", allocationSize = 1)
    private Long cpid;// 竞争对手管理主表id

    private String cpname;// 竞争对手名称

    @Temporal(TemporalType.DATE)
    private Calendar surveytime;// 调研时间
    private String surveydept;// 调研部门
    private String surveycity;// 调研城市
    private String brandgoal;// 品牌定位
    private String commercialmode;// 商业模式
    private String typicalprocon;// 独特优势/劣势
    private String cuser;// 创建者

    @Temporal(TemporalType.DATE)
    private Calendar ctime;// 创建时间

    @Transient
    private String cpnameName;

    @Transient
    private String surveydeptName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CustSurvey> custsurveyList;// 客户调研

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PersonnelInfo> perinfolist;// 人事信息

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChannelSurvey> chansurveyList;// 渠道调研

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MarActivitty> maractivityList;// 市场活动

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AdDeploy> adDeployList;// 广告投放

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GoodStatus> goodStatusList;// 竞品商品动态

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competitor", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SzNewStyles> newStyleList;// 新工艺款式动态

    public String getSurveydeptName() {
        return surveydeptName;
    }

    public void setSurveydeptName(String surveydeptName) {
        this.surveydeptName = surveydeptName;
    }

    public String getCpnameName() {
        return cpnameName;
    }

    public void setCpnameName(String cpnameName) {
        this.cpnameName = cpnameName;
    }

    public Calendar getSurveytime() {
        return surveytime;
    }

    public void setSurveytime(Calendar surveytime) {
        this.surveytime = surveytime;
    }

    public String getSurveydept() {
        return surveydept;
    }

    public void setSurveydept(String surveydept) {
        this.surveydept = surveydept;
    }

    public String getSurveycity() {
        return surveycity;
    }

    public void setSurveycity(String surveycity) {
        this.surveycity = surveycity;
    }

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getBrandgoal() {
        return brandgoal;
    }

    public void setBrandgoal(String brandgoal) {
        this.brandgoal = brandgoal;
    }

    public String getCommercialmode() {
        return commercialmode;
    }

    public void setCommercialmode(String commercialmode) {
        this.commercialmode = commercialmode;
    }

    public String getTypicalprocon() {
        return typicalprocon;
    }

    public void setTypicalprocon(String typicalprocon) {
        this.typicalprocon = typicalprocon;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public Calendar getCtime() {
        return ctime;
    }

    public void setCtime(Calendar ctime) {
        this.ctime = ctime;
    }

    public List<AdDeploy> getAdDeployList() {
        return adDeployList;
    }

    public void setAdDeployList(List<AdDeploy> adDeployList) {
        this.adDeployList = adDeployList;
    }

    public List<ChannelSurvey> getChansurveyList() {
        return chansurveyList;
    }

    public void setChansurveyList(List<ChannelSurvey> chansurveyList) {
        this.chansurveyList = chansurveyList;
    }

    public List<CustSurvey> getCustsurveyList() {
        return custsurveyList;
    }

    public void setCustsurveyList(List<CustSurvey> custsurveyList) {
        this.custsurveyList = custsurveyList;
    }

    public List<PersonnelInfo> getPerinfolist() {
        return perinfolist;
    }

    public void setPerinfolist(List<PersonnelInfo> perinfolist) {
        this.perinfolist = perinfolist;
    }

    public List<GoodStatus> getGoodStatusList() {
        return goodStatusList;
    }

    public void setGoodStatusList(List<GoodStatus> goodStatusList) {
        this.goodStatusList = goodStatusList;
    }

    public List<SzNewStyles> getNewStyleList() {
        return newStyleList;
    }

    public void setNewStyleList(List<SzNewStyles> newStyleList) {
        this.newStyleList = newStyleList;
    }

    public List<MarActivitty> getMaractivityList() {
        return maractivityList;
    }

    public void setMaractivityList(List<MarActivitty> maractivityList) {
        this.maractivityList = maractivityList;
    }

}