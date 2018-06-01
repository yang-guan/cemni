package com.huiju.expandbusi.salesmananalyze;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.expandbusi.salesmananalyze.entity.SalesmanAnalyze;
import com.huiju.expandbusi.salesmananalyze.entity.Storedetail;
import com.huiju.expandbusi.salesmananalyze.logic.SalesmanAnalyzeRemote;
import com.huiju.expandbusi.salesmananalyze.logic.StoredetailRemote;
import com.huiju.module.data.Page;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

// 业务员分解
public class SalesmanAnalyzeAction extends BaseAction<SalesmanAnalyze, Long> {
	private static final long serialVersionUID = 1L;

	@EJB(mappedName = "SalesmanAnalyzeBean")
	private SalesmanAnalyzeRemote salesmanAnalyzeLogic;

	@EJB(mappedName = "StoredetailBean")
	private StoredetailRemote StoredetailLogic;

	@EJB(mappedName = "SqlBean")
	private SqlRemote sqlLogic;

	public String init() throws Exception {
		jsPath.add("/js/expandbusi/salesmananalyze/Q.expandbusi.salesmananalyze.js");

		String[] authorities = { "D_SALESMANANALYZE_LIST", "D_SALESMANANALYZE_ADD", "D_PARTNER_DELETE", "D_SALESMANANALYZE_EDIT", "D_SALESMANANALYZE_SEARCH" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	public void getJson() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		Page<SalesmanAnalyze> page = new Page<SalesmanAnalyze>(start, limit, sort, dir);
		page = salesmanAnalyzeLogic.findAll(page, params);
		renderJson(page);
	}

	public String save() {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_year", model.getYear());
		long cnt = salesmanAnalyzeLogic.count(searchParams);
		if (cnt > 0) {
			return dealJson(false, "“指标年份”信息已存在！");
		}
		this.setOneToManyValue();
		model.setCuser(WebUtils.getUserName());
		String SalesmananalyzeNo = sqlLogic.getCnNum(GlobalConst.NUM_SALESMANANALYZE);
		model.setSalesmananalyzeNo(SalesmananalyzeNo);
		model.setCdate(Calendar.getInstance());
		model = salesmanAnalyzeLogic.persist(model);
		return dealJson(true);
	}

	public void edit() {
		model = salesmanAnalyzeLogic.findById(id);
		dealJson(true, model);
	}

	public void update() {
		SalesmanAnalyze sa = salesmanAnalyzeLogic.findById(model.getSalesmananalyzeId());
		model.setCdate(sa.getCdate());
		model.setCuser(sa.getCuser());
		model.setMuser(WebUtils.getUserName());
		model.setMdate(Calendar.getInstance());
		salesmanAnalyzeLogic.merge(model);
		dealJson(true);
	}

	// 级联删除
	public void delete() {
		for (Long id : ids) {
			salesmanAnalyzeLogic.removeById(id);
		}
		dealJson(true);
	}

	public void getRel() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List<Storedetail> rsList = StoredetailLogic.findAll(params);
		renderJson(rsList);
	}

	/**
	 * 设置从表
	 */
	private void setOneToManyValue() {
		if (null != this.model.getStoredetail()) {
			for (Storedetail sd : this.model.getStoredetail()) {
				sd.setSalesmananalyze(model);
			}
		}
	}

	/**
	 * 
	 * @param partYear
	 *            数据库年份索引
	 * @param partYearIndex
	 *            当前年份所属索引
	 * @return
	 */
	public Integer getPartYear(Integer partYear, Integer partYearIndex) {
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		return year + (partYear - partYearIndex);
	}

}