package com.huiju.expandbusi.salesmananalyze.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

/**
 * 业务员业绩分解
 * 
 * @author zzy
 * @date 2016年12月27日 下午4:48:31
 */
@Entity
@Table(name = "D_SALESMANANALYZE_DETAILED")
public class Storedetail extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "STOREDETAIL_PK")
    @TableGenerator(name = "STOREDETAIL_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "STOREDETAIL_PK", allocationSize = 1)
    private Long storedetailid;

    private String dept; //所属部门                
    private String salesman; //业务员姓名              
    private Integer type; //业绩类别                
    private Long janiexploit; //一月指标区域开发        
    private Integer janishop; //一月指标开店数          
    private Long janigoods; //一月指标首批铺货        
    private Long janfexploit; //一月完成情况区域开发    
    private Integer janfshop; //一月完成情况开店数      
    private Long janfgoods; //一月完成情况首批铺货    
    private Long febiexploit; //二月指标区域开发        
    private Integer febishop; //二月指标开店数          
    private Long febigoods; //二月指标首批铺货        
    private Long febfexploit; //二月完成情况区域开发    
    private Integer febfshop; //二月完成情况开店数      
    private Long febfgoods; //二月完成情况首批铺货    
    private Long mariexploit; //三月指标区域开发        
    private Integer marishop; //三月指标开店数          
    private Long marigoods; //三月指标首批铺货        
    private Long marfexploit; //三月完成情况区域开发    
    private Integer marfshop; //三月完成情况开店数      
    private Long marfgoods; //三月完成情况首批铺货    
    private Long apriexploit; //四月指标区域开发        
    private Integer aprishop; //四月指标开店数          
    private Long aprigoods; //四月指标首批铺货        
    private Long aprfexploit; //四月完成情况区域开发    
    private Integer aprfshop; //四月完成情况开店数      
    private Long aprfgoods; //四月完成情况首批铺货    
    private Long mayiexploit; //五月指标区域开发        
    private Integer mayishop; //五月指标开店数          
    private Long mayigoods; //五月指标首批铺货        
    private Long mayfexploit; //五月完成情况区域开发    
    private Integer mayfshop; //五月完成情况开店数      
    private Long mayfgoods; //五月完成情况首批铺货    
    private Long juniexploit; //六月指标区域开发        
    private Integer junishop; //六月指标开店数          
    private Long junigoods; //六月指标首批铺货        
    private Long junfexploit; //六月完成情况区域开发    
    private Integer junfshop; //六月完成情况开店数      
    private Long junfgoods; //六月完成情况首批铺货    
    private Long juliexploit; //七月指标区域开发        
    private Integer julishop; //七月指标开店数          
    private Long juligoods; //七月指标首批铺货        
    private Long julfexploit; //七月完成情况区域开发    
    private Integer julfshop; //七月完成情况开店数      
    private Long julfgoods; //七月完成情况首批铺货    
    private Long augiexploit; //八月指标区域开发        
    private Integer augishop; //八月指标开店数          
    private Long augigoods; //八月指标首批铺货        
    private Long augfexploit; //八月完成情况区域开发    
    private Integer augfshop; //八月完成情况开店数      
    private Long augfgoods; //八月完成情况首批铺货    
    private Long sepiexploit; //九月指标区域开发        
    private Integer sepishop; //九月指标开店数          
    private Long sepigoods; //九月指标首批铺货        
    private Long sepfexploit; //九月完成情况区域开发    
    private Integer sepfshop; //九月完成情况开店数      
    private Long sepfgoods; //九月完成情况首批铺货    
    private Long octiexploit; //十月指标区域开发        
    private Integer octishop; //十月指标开店数          
    private Long octigoods; //十月指标首批铺货        
    private Long octfexploit; //十月完成情况区域开发    
    private Integer octfshop; //十月完成情况开店数      
    private Long octfgoods; //十月完成情况首批铺货    
    private Long noviexploit; //十一月指标区域开发      
    private Integer novishop; //十一月指标开店数        
    private Long novigoods; //十一月指标首批铺货      
    private Long novfexploit; //十一月完成情况区域开发  
    private Integer novfshop; //十一月完成情况开店数    
    private Long novfgoods; //十一月完成情况首批铺货  
    private Long deciexploit; //十二月指标区域开发      
    private Integer decishop; //十二月月指标开店数      
    private Long decigoods; //十二月月指标首批铺货    
    private Long decfexploit; //十二月月完成情况区域开发
    private Integer decfshop; //十二月月完成情况开店数  
    private Long decfgoods; //十二月月完成情况首批铺货

    @ManyToOne
    @JoinColumn(name = "salesmananalyzeId", referencedColumnName = "salesmananalyzeId")
    private SalesmanAnalyze salesmananalyze;

	public Long getStoredetailid() {
		return storedetailid;
	}

	public void setStoredetailid(Long storedetailid) {
		this.storedetailid = storedetailid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getJaniexploit() {
		return janiexploit;
	}

	public void setJaniexploit(Long janiexploit) {
		this.janiexploit = janiexploit;
	}

	public Integer getJanishop() {
		return janishop;
	}

	public void setJanishop(Integer janishop) {
		this.janishop = janishop;
	}

	public Long getJanigoods() {
		return janigoods;
	}

	public void setJanigoods(Long janigoods) {
		this.janigoods = janigoods;
	}

	public Long getJanfexploit() {
		return janfexploit;
	}

	public void setJanfexploit(Long janfexploit) {
		this.janfexploit = janfexploit;
	}

	public Integer getJanfshop() {
		return janfshop;
	}

	public void setJanfshop(Integer janfshop) {
		this.janfshop = janfshop;
	}

	public Long getJanfgoods() {
		return janfgoods;
	}

	public void setJanfgoods(Long janfgoods) {
		this.janfgoods = janfgoods;
	}

	public Long getFebiexploit() {
		return febiexploit;
	}

	public void setFebiexploit(Long febiexploit) {
		this.febiexploit = febiexploit;
	}

	public Integer getFebishop() {
		return febishop;
	}

	public void setFebishop(Integer febishop) {
		this.febishop = febishop;
	}

	public Long getFebigoods() {
		return febigoods;
	}

	public void setFebigoods(Long febigoods) {
		this.febigoods = febigoods;
	}

	public Long getFebfexploit() {
		return febfexploit;
	}

	public void setFebfexploit(Long febfexploit) {
		this.febfexploit = febfexploit;
	}

	public Integer getFebfshop() {
		return febfshop;
	}

	public void setFebfshop(Integer febfshop) {
		this.febfshop = febfshop;
	}

	public Long getFebfgoods() {
		return febfgoods;
	}

	public void setFebfgoods(Long febfgoods) {
		this.febfgoods = febfgoods;
	}

	public Long getMariexploit() {
		return mariexploit;
	}

	public void setMariexploit(Long mariexploit) {
		this.mariexploit = mariexploit;
	}

	public Integer getMarishop() {
		return marishop;
	}

	public void setMarishop(Integer marishop) {
		this.marishop = marishop;
	}

	public Long getMarigoods() {
		return marigoods;
	}

	public void setMarigoods(Long marigoods) {
		this.marigoods = marigoods;
	}

	public Long getMarfexploit() {
		return marfexploit;
	}

	public void setMarfexploit(Long marfexploit) {
		this.marfexploit = marfexploit;
	}

	public Integer getMarfshop() {
		return marfshop;
	}

	public void setMarfshop(Integer marfshop) {
		this.marfshop = marfshop;
	}

	public Long getMarfgoods() {
		return marfgoods;
	}

	public void setMarfgoods(Long marfgoods) {
		this.marfgoods = marfgoods;
	}

	public Long getApriexploit() {
		return apriexploit;
	}

	public void setApriexploit(Long apriexploit) {
		this.apriexploit = apriexploit;
	}

	public Integer getAprishop() {
		return aprishop;
	}

	public void setAprishop(Integer aprishop) {
		this.aprishop = aprishop;
	}

	public Long getAprigoods() {
		return aprigoods;
	}

	public void setAprigoods(Long aprigoods) {
		this.aprigoods = aprigoods;
	}

	public Long getAprfexploit() {
		return aprfexploit;
	}

	public void setAprfexploit(Long aprfexploit) {
		this.aprfexploit = aprfexploit;
	}

	public Integer getAprfshop() {
		return aprfshop;
	}

	public void setAprfshop(Integer aprfshop) {
		this.aprfshop = aprfshop;
	}

	public Long getAprfgoods() {
		return aprfgoods;
	}

	public void setAprfgoods(Long aprfgoods) {
		this.aprfgoods = aprfgoods;
	}

	public Long getMayiexploit() {
		return mayiexploit;
	}

	public void setMayiexploit(Long mayiexploit) {
		this.mayiexploit = mayiexploit;
	}

	public Integer getMayishop() {
		return mayishop;
	}

	public void setMayishop(Integer mayishop) {
		this.mayishop = mayishop;
	}

	public Long getMayigoods() {
		return mayigoods;
	}

	public void setMayigoods(Long mayigoods) {
		this.mayigoods = mayigoods;
	}

	public Long getMayfexploit() {
		return mayfexploit;
	}

	public void setMayfexploit(Long mayfexploit) {
		this.mayfexploit = mayfexploit;
	}

	public Integer getMayfshop() {
		return mayfshop;
	}

	public void setMayfshop(Integer mayfshop) {
		this.mayfshop = mayfshop;
	}

	public Long getMayfgoods() {
		return mayfgoods;
	}

	public void setMayfgoods(Long mayfgoods) {
		this.mayfgoods = mayfgoods;
	}

	public Long getJuniexploit() {
		return juniexploit;
	}

	public void setJuniexploit(Long juniexploit) {
		this.juniexploit = juniexploit;
	}

	public Integer getJunishop() {
		return junishop;
	}

	public void setJunishop(Integer junishop) {
		this.junishop = junishop;
	}

	public Long getJunigoods() {
		return junigoods;
	}

	public void setJunigoods(Long junigoods) {
		this.junigoods = junigoods;
	}

	public Long getJunfexploit() {
		return junfexploit;
	}

	public void setJunfexploit(Long junfexploit) {
		this.junfexploit = junfexploit;
	}

	public Integer getJunfshop() {
		return junfshop;
	}

	public void setJunfshop(Integer junfshop) {
		this.junfshop = junfshop;
	}

	public Long getJunfgoods() {
		return junfgoods;
	}

	public void setJunfgoods(Long junfgoods) {
		this.junfgoods = junfgoods;
	}

	public Long getJuliexploit() {
		return juliexploit;
	}

	public void setJuliexploit(Long juliexploit) {
		this.juliexploit = juliexploit;
	}

	public Integer getJulishop() {
		return julishop;
	}

	public void setJulishop(Integer julishop) {
		this.julishop = julishop;
	}

	public Long getJuligoods() {
		return juligoods;
	}

	public void setJuligoods(Long juligoods) {
		this.juligoods = juligoods;
	}

	public Long getJulfexploit() {
		return julfexploit;
	}

	public void setJulfexploit(Long julfexploit) {
		this.julfexploit = julfexploit;
	}

	public Integer getJulfshop() {
		return julfshop;
	}

	public void setJulfshop(Integer julfshop) {
		this.julfshop = julfshop;
	}

	public Long getJulfgoods() {
		return julfgoods;
	}

	public void setJulfgoods(Long julfgoods) {
		this.julfgoods = julfgoods;
	}

	public Long getAugiexploit() {
		return augiexploit;
	}

	public void setAugiexploit(Long augiexploit) {
		this.augiexploit = augiexploit;
	}

	public Integer getAugishop() {
		return augishop;
	}

	public void setAugishop(Integer augishop) {
		this.augishop = augishop;
	}

	public Long getAugigoods() {
		return augigoods;
	}

	public void setAugigoods(Long augigoods) {
		this.augigoods = augigoods;
	}

	public Long getAugfexploit() {
		return augfexploit;
	}

	public void setAugfexploit(Long augfexploit) {
		this.augfexploit = augfexploit;
	}

	public Integer getAugfshop() {
		return augfshop;
	}

	public void setAugfshop(Integer augfshop) {
		this.augfshop = augfshop;
	}

	public Long getAugfgoods() {
		return augfgoods;
	}

	public void setAugfgoods(Long augfgoods) {
		this.augfgoods = augfgoods;
	}

	public Long getSepiexploit() {
		return sepiexploit;
	}

	public void setSepiexploit(Long sepiexploit) {
		this.sepiexploit = sepiexploit;
	}

	public Integer getSepishop() {
		return sepishop;
	}

	public void setSepishop(Integer sepishop) {
		this.sepishop = sepishop;
	}

	public Long getSepigoods() {
		return sepigoods;
	}

	public void setSepigoods(Long sepigoods) {
		this.sepigoods = sepigoods;
	}

	public Long getSepfexploit() {
		return sepfexploit;
	}

	public void setSepfexploit(Long sepfexploit) {
		this.sepfexploit = sepfexploit;
	}

	public Integer getSepfshop() {
		return sepfshop;
	}

	public void setSepfshop(Integer sepfshop) {
		this.sepfshop = sepfshop;
	}

	public Long getSepfgoods() {
		return sepfgoods;
	}

	public void setSepfgoods(Long sepfgoods) {
		this.sepfgoods = sepfgoods;
	}

	public Long getOctiexploit() {
		return octiexploit;
	}

	public void setOctiexploit(Long octiexploit) {
		this.octiexploit = octiexploit;
	}

	public Integer getOctishop() {
		return octishop;
	}

	public void setOctishop(Integer octishop) {
		this.octishop = octishop;
	}

	public Long getOctigoods() {
		return octigoods;
	}

	public void setOctigoods(Long octigoods) {
		this.octigoods = octigoods;
	}

	public Long getOctfexploit() {
		return octfexploit;
	}

	public void setOctfexploit(Long octfexploit) {
		this.octfexploit = octfexploit;
	}

	public Integer getOctfshop() {
		return octfshop;
	}

	public void setOctfshop(Integer octfshop) {
		this.octfshop = octfshop;
	}

	public Long getOctfgoods() {
		return octfgoods;
	}

	public void setOctfgoods(Long octfgoods) {
		this.octfgoods = octfgoods;
	}

	public Long getNoviexploit() {
		return noviexploit;
	}

	public void setNoviexploit(Long noviexploit) {
		this.noviexploit = noviexploit;
	}

	public Integer getNovishop() {
		return novishop;
	}

	public void setNovishop(Integer novishop) {
		this.novishop = novishop;
	}

	public Long getNovigoods() {
		return novigoods;
	}

	public void setNovigoods(Long novigoods) {
		this.novigoods = novigoods;
	}

	public Long getNovfexploit() {
		return novfexploit;
	}

	public void setNovfexploit(Long novfexploit) {
		this.novfexploit = novfexploit;
	}

	public Integer getNovfshop() {
		return novfshop;
	}

	public void setNovfshop(Integer novfshop) {
		this.novfshop = novfshop;
	}

	public Long getNovfgoods() {
		return novfgoods;
	}

	public void setNovfgoods(Long novfgoods) {
		this.novfgoods = novfgoods;
	}

	public Long getDeciexploit() {
		return deciexploit;
	}

	public void setDeciexploit(Long deciexploit) {
		this.deciexploit = deciexploit;
	}

	public Integer getDecishop() {
		return decishop;
	}

	public void setDecishop(Integer decishop) {
		this.decishop = decishop;
	}

	public Long getDecigoods() {
		return decigoods;
	}

	public void setDecigoods(Long decigoods) {
		this.decigoods = decigoods;
	}

	public Long getDecfexploit() {
		return decfexploit;
	}

	public void setDecfexploit(Long decfexploit) {
		this.decfexploit = decfexploit;
	}

	public Integer getDecfshop() {
		return decfshop;
	}

	public void setDecfshop(Integer decfshop) {
		this.decfshop = decfshop;
	}

	public Long getDecfgoods() {
		return decfgoods;
	}

	public void setDecfgoods(Long decfgoods) {
		this.decfgoods = decfgoods;
	}

	public SalesmanAnalyze getSalesmananalyze() {
		return salesmananalyze;
	}

	public void setSalesmananalyze(SalesmanAnalyze salesmananalyze) {
		this.salesmananalyze = salesmananalyze;
	}

   
}