package com.huiju.contract;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.contract.logic.ContractRemote;
import com.huiju.contract.terms.entity.ContractTerms;
import com.huiju.contract.terms.logic.ContractTermsRemote;
import com.huiju.module.data.Page;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.utils.NeuUtils;

public class ContractTermsAction extends BaseAction<ContractTerms, Long> {
	private static final long serialVersionUID = 1L;
	@EJB
	private ContractTermsRemote contractTermsLogic;
	@EJB
	private ContractRemote contractLogic;
	@EJB
	private SqlRemote sqlLogic;
	@EJB
	private User2orgRemote user2orgLogic;

	public String init() {
		jsPath.add("/js/contract/Q.contract.terms.js");

		String[] authorities = { "D_CONTRACTTERMS_LIST", "D_CONTRACTTERMS_ADD", "D_CONTRACTTERMS_SEARCH",
				"D_CONTRACTTERMS_DELETE", "D_CONTRACTTERMS_EDIT" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	@SuppressWarnings("rawtypes")
	public void getJson() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		Page<ContractTerms> page = new Page<ContractTerms>(start, limit, NeuUtils.chgQrySort(sort), dir);
		Map<String, Object> userParams = new HashMap<String, Object>();
		userParams.put("EQ_userId", WebUtils.getUserId());
		List rsList = user2orgLogic.qryOrgByUserIdAndParms(userParams);
		Set<Integer> set = contractLogic.getContractType(rsList);
		String strParams = StringUtils.join(set.toArray(), ",");
		params.put("IN_termsType", strParams);
		Object LE_createDate = params.get("LE_createDate");
		if (LE_createDate != null && !StringUtils.isEmpty(LE_createDate.toString())) {
			Calendar cl = NeuUtils.parseCalendar(LE_createDate.toString());
			cl.add(Calendar.DATE, 1);
			params.put("LE_createDate", NeuUtils.parseStringFromCalendar(cl));
		}
		page = contractTermsLogic.findAll(page, params);
		for (ContractTerms dt : page) {
			dt.setTermsTypeName(DataDict.getDictName(DataDict.CONTRACT_CONTRACT, dt.getTermsType()));
		}
		renderJson(page);
	}

	public void save() {
		model.setCreateUser(WebUtils.getUserName());
		model.setCreateDate(Calendar.getInstance());
		model.setTermsNum(sqlLogic.getCnNum(GlobalConst.NUM_CONTRACT));
		model = contractTermsLogic.persist(model);
		dealJson(true);
	}

	public void edit() {
		model = contractTermsLogic.findById(id);
		dealJson(true, model);
	}

	public void update() {
		model = contractTermsLogic.merge(model);
		dealJson(true, model);
	}

}