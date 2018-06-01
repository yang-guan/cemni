package com.huiju.expandbusi.memcompanalyze.entity;

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

@Entity
@Table(name = "D_MEMCOMPANALYZE_DETAIL")
public class Memdetail extends BaseEntity<Double> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMDETAIL_PK")
    @TableGenerator(name = "MEMDETAIL_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "MEMDETAIL_PK", allocationSize = 1)
    private Double memdetailId;

    @ManyToOne
    @JoinColumn(name = "memcompanalyzeId", referencedColumnName = "memcompanalyzeId")
    private MemCompAnalyze memCompAnalyze;

    // 需要存储老数据
    // 大区
    private Long bigAreaId;
    private String bigAreaNo;
    private String bigAreaName;
    // 区域
    private Long areaId;
    private String areaNo;
    private String areaName;
    // 门店
    private Long storeId;
    private String storeNo;
    private String name;
    private Integer attr;
    @Transient
    private String attrName;

    private Integer janipeople;// 一月新会员人数        
    private Double janiprice;// 一月新会员客单价      
    private Integer janfpeople;// 一月老会员人数        
    private Double janfprice;// 一月老会员客单价     

    private Integer febipeople;// 二月新会员人数        
    private Double febiprice;// 二月新会员客单价      
    private Integer febfpeople;// 二月老会员人数        
    private Double febfprice;// 二月老会员客单价    

    private Integer maripeople;// 三月新会员人数        
    private Double mariprice;// 三月新会员客单价      
    private Integer marfpeople;// 三月老会员人数        
    private Double marfprice;// 三月老会员客单价      

    private Integer apripeople;// 四月新会员人数        
    private Double apriprice;// 四月新会员客单价      
    private Integer aprfpeople;// 四月老会员人数        
    private Double aprfprice;// 四月老会员客单价      

    private Integer mayipeople;// 五月新会员人数        
    private Double mayiprice;// 五月新会员客单价      
    private Integer mayfpeople;// 五月老会员人数        
    private Double mayfprice;// 五月老会员客单价      

    private Integer junipeople;// 六月新会员人数        
    private Double juniprice;// 六月新会员客单价      
    private Integer junfpeople;// 六月老会员人数        
    private Double junfprice;// 六月老会员客单价      

    private Integer julipeople;// 七月新会员人数        
    private Double juliprice;// 七月新会员客单价      
    private Integer julfpeople;// 七月老会员人数        
    private Double julfprice;// 七月老会员客单价      

    private Integer augipeople;// 八月新会员人数        
    private Double augiprice;// 八月新会员客单价      
    private Integer augfpeople;// 八月老会员人数        
    private Double augfprice;// 八月老会员客单价    

    private Integer sepipeople;// 九月新会员人数        
    private Double sepiprice;// 九月新会员客单价      
    private Integer sepfpeople;// 九月老会员人数        
    private Double sepfprice;// 九月老会员客单价    

    private Integer octipeople;// 十月新会员人数        
    private Double octiprice;// 十月新会员客单价      
    private Integer octfpeople;// 十月老会员人数        
    private Double octfprice;// 十月老会员客单价     

    private Integer novipeople;// 十一月新会员人数      
    private Double noviprice;// 十一月新会员客单价    
    private Integer novfpeople;// 十一月老会员人数      
    private Double novfprice;// 十一月老会员客单价    

    private Integer decipeople;// 十二月新会员人数      
    private Double deciprice;// 十二月新会员客单价    
    private Integer decfpeople;// 十二月老会员人数      
    private Double decfprice;// 十二月老会员客单价

    public Double getMemdetailId() {
        return memdetailId;
    }

    public void setMemdetailId(Double memdetailId) {
        this.memdetailId = memdetailId;
    }

    public MemCompAnalyze getMemCompAnalyze() {
        return memCompAnalyze;
    }

    public void setMemCompAnalyze(MemCompAnalyze memCompAnalyze) {
        this.memCompAnalyze = memCompAnalyze;
    }

    public Long getBigAreaId() {
        return bigAreaId;
    }

    public void setBigAreaId(Long bigAreaId) {
        this.bigAreaId = bigAreaId;
    }

    public String getBigAreaNo() {
        return bigAreaNo;
    }

    public void setBigAreaNo(String bigAreaNo) {
        this.bigAreaNo = bigAreaNo;
    }

    public String getBigAreaName() {
        return bigAreaName;
    }

    public void setBigAreaName(String bigAreaName) {
        this.bigAreaName = bigAreaName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttr() {
        return attr;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Integer getJanipeople() {
        return janipeople;
    }

    public void setJanipeople(Integer janipeople) {
        this.janipeople = janipeople;
    }

    public Double getJaniprice() {
        return janiprice;
    }

    public void setJaniprice(Double janiprice) {
        this.janiprice = janiprice;
    }

    public Integer getJanfpeople() {
        return janfpeople;
    }

    public void setJanfpeople(Integer janfpeople) {
        this.janfpeople = janfpeople;
    }

    public Double getJanfprice() {
        return janfprice;
    }

    public void setJanfprice(Double janfprice) {
        this.janfprice = janfprice;
    }

    public Integer getFebipeople() {
        return febipeople;
    }

    public void setFebipeople(Integer febipeople) {
        this.febipeople = febipeople;
    }

    public Double getFebiprice() {
        return febiprice;
    }

    public void setFebiprice(Double febiprice) {
        this.febiprice = febiprice;
    }

    public Integer getFebfpeople() {
        return febfpeople;
    }

    public void setFebfpeople(Integer febfpeople) {
        this.febfpeople = febfpeople;
    }

    public Double getFebfprice() {
        return febfprice;
    }

    public void setFebfprice(Double febfprice) {
        this.febfprice = febfprice;
    }

    public Integer getMaripeople() {
        return maripeople;
    }

    public void setMaripeople(Integer maripeople) {
        this.maripeople = maripeople;
    }

    public Double getMariprice() {
        return mariprice;
    }

    public void setMariprice(Double mariprice) {
        this.mariprice = mariprice;
    }

    public Integer getMarfpeople() {
        return marfpeople;
    }

    public void setMarfpeople(Integer marfpeople) {
        this.marfpeople = marfpeople;
    }

    public Double getMarfprice() {
        return marfprice;
    }

    public void setMarfprice(Double marfprice) {
        this.marfprice = marfprice;
    }

    public Integer getApripeople() {
        return apripeople;
    }

    public void setApripeople(Integer apripeople) {
        this.apripeople = apripeople;
    }

    public Double getApriprice() {
        return apriprice;
    }

    public void setApriprice(Double apriprice) {
        this.apriprice = apriprice;
    }

    public Integer getAprfpeople() {
        return aprfpeople;
    }

    public void setAprfpeople(Integer aprfpeople) {
        this.aprfpeople = aprfpeople;
    }

    public Double getAprfprice() {
        return aprfprice;
    }

    public void setAprfprice(Double aprfprice) {
        this.aprfprice = aprfprice;
    }

    public Integer getMayipeople() {
        return mayipeople;
    }

    public void setMayipeople(Integer mayipeople) {
        this.mayipeople = mayipeople;
    }

    public Double getMayiprice() {
        return mayiprice;
    }

    public void setMayiprice(Double mayiprice) {
        this.mayiprice = mayiprice;
    }

    public Integer getMayfpeople() {
        return mayfpeople;
    }

    public void setMayfpeople(Integer mayfpeople) {
        this.mayfpeople = mayfpeople;
    }

    public Double getMayfprice() {
        return mayfprice;
    }

    public void setMayfprice(Double mayfprice) {
        this.mayfprice = mayfprice;
    }

    public Integer getJunipeople() {
        return junipeople;
    }

    public void setJunipeople(Integer junipeople) {
        this.junipeople = junipeople;
    }

    public Double getJuniprice() {
        return juniprice;
    }

    public void setJuniprice(Double juniprice) {
        this.juniprice = juniprice;
    }

    public Integer getJunfpeople() {
        return junfpeople;
    }

    public void setJunfpeople(Integer junfpeople) {
        this.junfpeople = junfpeople;
    }

    public Double getJunfprice() {
        return junfprice;
    }

    public void setJunfprice(Double junfprice) {
        this.junfprice = junfprice;
    }

    public Integer getJulipeople() {
        return julipeople;
    }

    public void setJulipeople(Integer julipeople) {
        this.julipeople = julipeople;
    }

    public Double getJuliprice() {
        return juliprice;
    }

    public void setJuliprice(Double juliprice) {
        this.juliprice = juliprice;
    }

    public Integer getJulfpeople() {
        return julfpeople;
    }

    public void setJulfpeople(Integer julfpeople) {
        this.julfpeople = julfpeople;
    }

    public Double getJulfprice() {
        return julfprice;
    }

    public void setJulfprice(Double julfprice) {
        this.julfprice = julfprice;
    }

    public Integer getAugipeople() {
        return augipeople;
    }

    public void setAugipeople(Integer augipeople) {
        this.augipeople = augipeople;
    }

    public Double getAugiprice() {
        return augiprice;
    }

    public void setAugiprice(Double augiprice) {
        this.augiprice = augiprice;
    }

    public Integer getAugfpeople() {
        return augfpeople;
    }

    public void setAugfpeople(Integer augfpeople) {
        this.augfpeople = augfpeople;
    }

    public Double getAugfprice() {
        return augfprice;
    }

    public void setAugfprice(Double augfprice) {
        this.augfprice = augfprice;
    }

    public Integer getSepipeople() {
        return sepipeople;
    }

    public void setSepipeople(Integer sepipeople) {
        this.sepipeople = sepipeople;
    }

    public Double getSepiprice() {
        return sepiprice;
    }

    public void setSepiprice(Double sepiprice) {
        this.sepiprice = sepiprice;
    }

    public Integer getSepfpeople() {
        return sepfpeople;
    }

    public void setSepfpeople(Integer sepfpeople) {
        this.sepfpeople = sepfpeople;
    }

    public Double getSepfprice() {
        return sepfprice;
    }

    public void setSepfprice(Double sepfprice) {
        this.sepfprice = sepfprice;
    }

    public Integer getOctipeople() {
        return octipeople;
    }

    public void setOctipeople(Integer octipeople) {
        this.octipeople = octipeople;
    }

    public Double getOctiprice() {
        return octiprice;
    }

    public void setOctiprice(Double octiprice) {
        this.octiprice = octiprice;
    }

    public Integer getOctfpeople() {
        return octfpeople;
    }

    public void setOctfpeople(Integer octfpeople) {
        this.octfpeople = octfpeople;
    }

    public Double getOctfprice() {
        return octfprice;
    }

    public void setOctfprice(Double octfprice) {
        this.octfprice = octfprice;
    }

    public Integer getNovipeople() {
        return novipeople;
    }

    public void setNovipeople(Integer novipeople) {
        this.novipeople = novipeople;
    }

    public Double getNoviprice() {
        return noviprice;
    }

    public void setNoviprice(Double noviprice) {
        this.noviprice = noviprice;
    }

    public Integer getNovfpeople() {
        return novfpeople;
    }

    public void setNovfpeople(Integer novfpeople) {
        this.novfpeople = novfpeople;
    }

    public Double getNovfprice() {
        return novfprice;
    }

    public void setNovfprice(Double novfprice) {
        this.novfprice = novfprice;
    }

    public Integer getDecipeople() {
        return decipeople;
    }

    public void setDecipeople(Integer decipeople) {
        this.decipeople = decipeople;
    }

    public Double getDeciprice() {
        return deciprice;
    }

    public void setDeciprice(Double deciprice) {
        this.deciprice = deciprice;
    }

    public Integer getDecfpeople() {
        return decfpeople;
    }

    public void setDecfpeople(Integer decfpeople) {
        this.decfpeople = decfpeople;
    }

    public Double getDecfprice() {
        return decfprice;
    }

    public void setDecfprice(Double decfprice) {
        this.decfprice = decfprice;
    }

}