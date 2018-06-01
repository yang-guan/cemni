package com.huiju.expandbusi.agentanalyze.entity;

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
 * 加盟商指标明细
 * 
 * @author zzy
 * @date 2016年11月30日 上午9:49:07
 */
@Entity
@Table(name = "D_AGENTANALYZE_DETAILED")
public class Detailed extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DETAILED_PK")
    @TableGenerator(name = "DETAILED_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "DETAILED_PK", allocationSize = 1)
    private Long detailedid;
    /**
     * 所属部门
     */
    private String dept;

    private Long deptId;
    private Long regionId;
    /**
     * 所属区域
     */
    private String region;
    /**
     * 加盟商名称
     */
    private String franame;
    /**
     * 加盟商名称ID
     */
    private Long fraId;
	/**
     * 一月指标铺货
     */
    private Long janigoods;
    /**
     * 一月指标开店数
     */
    private Integer janishop;
    /**
     * 一月完成情况铺货
     */
    private Long janfgoods;
    /**
     * 一月完成情况开店数
     */
    private Integer janfshop;
    /**
     * 一月完成情况年度累计拿货额
     */
    private Long janfgoodstake;
    /**
     * 二月指标铺货
     */
    private Long febigoods;
    /**
     * 二月指标开店数
     */
    private Integer febishop;
    /**
     * 二月完成情况铺货
     */
    private Long febfgoods;
    /**
     * 二月完成情况开店数
     */
    private Integer febfshop;
    /**
     * 二月完成情况年度累计拿货额
     */
    private Long febfgoodstake;
    /**
     * 三月指标铺货
     */
    private Long marigoods;
    /**
     * 三月指标开店数
     */
    private Integer marishop;
    /**
     * 三月完成情况铺货
     */
    private Long marfgoods;
    /**
     * 三月完成情况开店数
     */
    private Integer marfshop;
    /**
     * 三月完成情况年度累计拿货额
     */
    private Long marfgoodstake;
    /**
     * 四月指标铺货
     */
    private Long aprigoods;
    /**
     * 四月指标开店数
     */
    private Integer aprishop;
    /**
     * 四月完成情况铺货
     */
    private Long aprfgoods;
    /**
     * 四月完成情况开店数
     */
    private Integer aprfshop;
    /**
     * 四月完成情况年度累计拿货额
     */
    private Long aprfgoodstake;
    /**
     * 五月指标铺货
     */
    private Long mayigoods;
    /**
     * 五月指标开店数
     */
    private Integer mayishop;
    /**
     * 五月完成情况铺货
     */
    private Long mayfgoods;
    /**
     * 五月完成情况开店数
     */
    private Integer mayfshop;
    /**
     * 五月完成情况年度累计拿货额
     */
    private Long mayfgoodstake;
    /**
     * 六月指标铺货
     */
    private Long junigoods;
    /**
     * 六月指标开店数
     */
    private Integer junishop;
    /**
     * 六月完成情况铺货
     */
    private Long junfgoods;
    /**
     * 六月完成情况开店数
     */
    private Integer junfshop;
    /**
     * 六月完成情况年度累计拿货额
     */
    private Long junfgoodstake;
    /**
     * 七月指标铺货
     */
    private Long juligoods;
    /**
     * 七月指标开店数
     */
    private Integer julishop;
    /**
     * 七月完成情况铺货
     */
    private Long julfgoods;
    /**
     * 七月完成情况开店数
     */
    private Integer julfshop;
    /**
     * 七月完成情况年度累计拿货额
     */
    private Long julfgoodstake;
    /**
     * 八月指标铺货
     */
    private Long augigoods;
    /**
     * 八月指标开店数
     */
    private Integer augishop;
    /**
     * 八月完成情况铺货
     */
    private Long augfgoods;
    /**
     * 八月完成情况开店数
     */
    private Integer augfshop;
    /**
     * 八月完成情况年度累计拿货额
     */
    private Long augfgoodstake;
    /**
     * 九月指标铺货
     */
    private Long sepigoods;
    /**
     * 九月指标开店数
     */
    private Integer sepishop;
    /**
     * 九月完成情况铺货
     */
    private Long sepfgoods;
    /**
     * 九月完成情况开店数
     */
    private Integer sepfshop;
    /**
     * 九月完成情况年度累计拿货额
     */
    private Long sepfgoodstake;
    /**
     * 十月指标铺货
     */
    private Long octigoods;
    /**
     * 十月指标开店数
     */
    private Integer octishop;
    /**
     * 十月完成情况铺货
     */
    private Long octfgoods;
    /**
     * 十月完成情况开店数
     */
    private Integer octfshop;
    /**
     * 十月完成情况年度累计拿货额
     */
    private Long octfgoodstake;
    /**
     * 十一月指标铺货
     */
    private Long novigoods;
    /**
     * 十一月指标开店数
     */
    private Integer novishop;
    /**
     * 十一月完成情况铺货
     */
    private Long novfgoods;
    /**
     * 十一月完成情况开店数
     */
    private Integer novfshop;
    /**
     * 十一月完成情况年度累计拿货额
     */
    private Long novfgoodstake;
    /**
     * 十二月指标铺货
     */
    private Long decigoods;
    /**
     * 十二月指标开店数
     */
    private Integer decishop;
    /**
     * 十二月完成情况铺货
     */
    private Long decfgoods;
    /**
     * 十二月完成情况开店数
     */
    private Integer decfshop;
    /**
     * 十二月完成情况年度累计拿货额
     */
    private Long decfgoodstake;

    @ManyToOne
    @JoinColumn(name = "agentanalyzeid", referencedColumnName = "agentanalyzeid")
    private Agentanalyze agentanalyze;

	public Long getDetailedid() {
		return detailedid;
	}

	public void setDetailedid(Long detailedid) {
		this.detailedid = detailedid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFraname() {
		return franame;
	}

	public void setFraname(String franame) {
		this.franame = franame;
	}

	public Long getFraId() {
		return fraId;
	}

	public void setFraId(Long fraId) {
		this.fraId = fraId;
	}

	public Long getJanigoods() {
		return janigoods;
	}

	public void setJanigoods(Long janigoods) {
		this.janigoods = janigoods;
	}

	public Integer getJanishop() {
		return janishop;
	}

	public void setJanishop(Integer janishop) {
		this.janishop = janishop;
	}

	public Long getJanfgoods() {
		return janfgoods;
	}

	public void setJanfgoods(Long janfgoods) {
		this.janfgoods = janfgoods;
	}

	public Integer getJanfshop() {
		return janfshop;
	}

	public void setJanfshop(Integer janfshop) {
		this.janfshop = janfshop;
	}

	public Long getJanfgoodstake() {
		return janfgoodstake;
	}

	public void setJanfgoodstake(Long janfgoodstake) {
		this.janfgoodstake = janfgoodstake;
	}

	public Long getFebigoods() {
		return febigoods;
	}

	public void setFebigoods(Long febigoods) {
		this.febigoods = febigoods;
	}

	public Integer getFebishop() {
		return febishop;
	}

	public void setFebishop(Integer febishop) {
		this.febishop = febishop;
	}

	public Long getFebfgoods() {
		return febfgoods;
	}

	public void setFebfgoods(Long febfgoods) {
		this.febfgoods = febfgoods;
	}

	public Integer getFebfshop() {
		return febfshop;
	}

	public void setFebfshop(Integer febfshop) {
		this.febfshop = febfshop;
	}

	public Long getFebfgoodstake() {
		return febfgoodstake;
	}

	public void setFebfgoodstake(Long febfgoodstake) {
		this.febfgoodstake = febfgoodstake;
	}

	public Long getMarigoods() {
		return marigoods;
	}

	public void setMarigoods(Long marigoods) {
		this.marigoods = marigoods;
	}

	public Integer getMarishop() {
		return marishop;
	}

	public void setMarishop(Integer marishop) {
		this.marishop = marishop;
	}

	public Long getMarfgoods() {
		return marfgoods;
	}

	public void setMarfgoods(Long marfgoods) {
		this.marfgoods = marfgoods;
	}

	public Integer getMarfshop() {
		return marfshop;
	}

	public void setMarfshop(Integer marfshop) {
		this.marfshop = marfshop;
	}

	public Long getMarfgoodstake() {
		return marfgoodstake;
	}

	public void setMarfgoodstake(Long marfgoodstake) {
		this.marfgoodstake = marfgoodstake;
	}

	public Long getAprigoods() {
		return aprigoods;
	}

	public void setAprigoods(Long aprigoods) {
		this.aprigoods = aprigoods;
	}

	public Integer getAprishop() {
		return aprishop;
	}

	public void setAprishop(Integer aprishop) {
		this.aprishop = aprishop;
	}

	public Long getAprfgoods() {
		return aprfgoods;
	}

	public void setAprfgoods(Long aprfgoods) {
		this.aprfgoods = aprfgoods;
	}

	public Integer getAprfshop() {
		return aprfshop;
	}

	public void setAprfshop(Integer aprfshop) {
		this.aprfshop = aprfshop;
	}

	public Long getAprfgoodstake() {
		return aprfgoodstake;
	}

	public void setAprfgoodstake(Long aprfgoodstake) {
		this.aprfgoodstake = aprfgoodstake;
	}

	public Long getMayigoods() {
		return mayigoods;
	}

	public void setMayigoods(Long mayigoods) {
		this.mayigoods = mayigoods;
	}

	public Integer getMayishop() {
		return mayishop;
	}

	public void setMayishop(Integer mayishop) {
		this.mayishop = mayishop;
	}

	public Long getMayfgoods() {
		return mayfgoods;
	}

	public void setMayfgoods(Long mayfgoods) {
		this.mayfgoods = mayfgoods;
	}

	public Integer getMayfshop() {
		return mayfshop;
	}

	public void setMayfshop(Integer mayfshop) {
		this.mayfshop = mayfshop;
	}

	public Long getMayfgoodstake() {
		return mayfgoodstake;
	}

	public void setMayfgoodstake(Long mayfgoodstake) {
		this.mayfgoodstake = mayfgoodstake;
	}

	public Long getJunigoods() {
		return junigoods;
	}

	public void setJunigoods(Long junigoods) {
		this.junigoods = junigoods;
	}

	public Integer getJunishop() {
		return junishop;
	}

	public void setJunishop(Integer junishop) {
		this.junishop = junishop;
	}

	public Long getJunfgoods() {
		return junfgoods;
	}

	public void setJunfgoods(Long junfgoods) {
		this.junfgoods = junfgoods;
	}

	public Integer getJunfshop() {
		return junfshop;
	}

	public void setJunfshop(Integer junfshop) {
		this.junfshop = junfshop;
	}

	public Long getJunfgoodstake() {
		return junfgoodstake;
	}

	public void setJunfgoodstake(Long junfgoodstake) {
		this.junfgoodstake = junfgoodstake;
	}

	public Long getJuligoods() {
		return juligoods;
	}

	public void setJuligoods(Long juligoods) {
		this.juligoods = juligoods;
	}

	public Integer getJulishop() {
		return julishop;
	}

	public void setJulishop(Integer julishop) {
		this.julishop = julishop;
	}

	public Long getJulfgoods() {
		return julfgoods;
	}

	public void setJulfgoods(Long julfgoods) {
		this.julfgoods = julfgoods;
	}

	public Integer getJulfshop() {
		return julfshop;
	}

	public void setJulfshop(Integer julfshop) {
		this.julfshop = julfshop;
	}

	public Long getJulfgoodstake() {
		return julfgoodstake;
	}

	public void setJulfgoodstake(Long julfgoodstake) {
		this.julfgoodstake = julfgoodstake;
	}

	public Long getAugigoods() {
		return augigoods;
	}

	public void setAugigoods(Long augigoods) {
		this.augigoods = augigoods;
	}

	public Integer getAugishop() {
		return augishop;
	}

	public void setAugishop(Integer augishop) {
		this.augishop = augishop;
	}

	public Long getAugfgoods() {
		return augfgoods;
	}

	public void setAugfgoods(Long augfgoods) {
		this.augfgoods = augfgoods;
	}

	public Integer getAugfshop() {
		return augfshop;
	}

	public void setAugfshop(Integer augfshop) {
		this.augfshop = augfshop;
	}

	public Long getAugfgoodstake() {
		return augfgoodstake;
	}

	public void setAugfgoodstake(Long augfgoodstake) {
		this.augfgoodstake = augfgoodstake;
	}

	public Long getSepigoods() {
		return sepigoods;
	}

	public void setSepigoods(Long sepigoods) {
		this.sepigoods = sepigoods;
	}

	public Integer getSepishop() {
		return sepishop;
	}

	public void setSepishop(Integer sepishop) {
		this.sepishop = sepishop;
	}

	public Long getSepfgoods() {
		return sepfgoods;
	}

	public void setSepfgoods(Long sepfgoods) {
		this.sepfgoods = sepfgoods;
	}

	public Integer getSepfshop() {
		return sepfshop;
	}

	public void setSepfshop(Integer sepfshop) {
		this.sepfshop = sepfshop;
	}

	public Long getSepfgoodstake() {
		return sepfgoodstake;
	}

	public void setSepfgoodstake(Long sepfgoodstake) {
		this.sepfgoodstake = sepfgoodstake;
	}

	public Long getOctigoods() {
		return octigoods;
	}

	public void setOctigoods(Long octigoods) {
		this.octigoods = octigoods;
	}

	public Integer getOctishop() {
		return octishop;
	}

	public void setOctishop(Integer octishop) {
		this.octishop = octishop;
	}

	public Long getOctfgoods() {
		return octfgoods;
	}

	public void setOctfgoods(Long octfgoods) {
		this.octfgoods = octfgoods;
	}

	public Integer getOctfshop() {
		return octfshop;
	}

	public void setOctfshop(Integer octfshop) {
		this.octfshop = octfshop;
	}

	public Long getOctfgoodstake() {
		return octfgoodstake;
	}

	public void setOctfgoodstake(Long octfgoodstake) {
		this.octfgoodstake = octfgoodstake;
	}

	public Long getNovigoods() {
		return novigoods;
	}

	public void setNovigoods(Long novigoods) {
		this.novigoods = novigoods;
	}

	public Integer getNovishop() {
		return novishop;
	}

	public void setNovishop(Integer novishop) {
		this.novishop = novishop;
	}

	public Long getNovfgoods() {
		return novfgoods;
	}

	public void setNovfgoods(Long novfgoods) {
		this.novfgoods = novfgoods;
	}

	public Integer getNovfshop() {
		return novfshop;
	}

	public void setNovfshop(Integer novfshop) {
		this.novfshop = novfshop;
	}

	public Long getNovfgoodstake() {
		return novfgoodstake;
	}

	public void setNovfgoodstake(Long novfgoodstake) {
		this.novfgoodstake = novfgoodstake;
	}

	public Long getDecigoods() {
		return decigoods;
	}

	public void setDecigoods(Long decigoods) {
		this.decigoods = decigoods;
	}

	public Integer getDecishop() {
		return decishop;
	}

	public void setDecishop(Integer decishop) {
		this.decishop = decishop;
	}

	public Long getDecfgoods() {
		return decfgoods;
	}

	public void setDecfgoods(Long decfgoods) {
		this.decfgoods = decfgoods;
	}

	public Integer getDecfshop() {
		return decfshop;
	}

	public void setDecfshop(Integer decfshop) {
		this.decfshop = decfshop;
	}

	public Long getDecfgoodstake() {
		return decfgoodstake;
	}

	public void setDecfgoodstake(Long decfgoodstake) {
		this.decfgoodstake = decfgoodstake;
	}

	public Agentanalyze getAgentanalyze() {
		return agentanalyze;
	}

	public void setAgentanalyze(Agentanalyze agentanalyze) {
		this.agentanalyze = agentanalyze;
	}

   
}