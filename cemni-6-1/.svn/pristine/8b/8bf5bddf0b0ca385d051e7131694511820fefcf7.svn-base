package com.huiju.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.console.dict.entity.Dict;
import com.huiju.console.dict.logic.DictRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.contract.logic.ContractRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;

/**
 * 字典表
 * 
 * @author：yuhb
 * @date：2016年11月24日 下午3:44:26
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DictAction extends BaseAction<Dict, Long> {
	private static final long serialVersionUID = 1L;
	@EJB
	private DictRemote appLogic;
	@EJB
	private User2orgRemote user2orgLogic;
	@EJB
	private ContractRemote contractLogic;

	/**
	 * 字典表：下拉框
	 */
	public void selDict() throws Exception {
		// 参数 dictCode 优先于 filter_EQ_dictCode
		String dictCodeStr = request.getParameter("dictCode");
		if (StringUtils.isEmpty(dictCodeStr)) {
			Map searchParam = WebUtils.getParametersStartingWith(request);
			dictCodeStr = searchParam.get("EQ_dictCode").toString();
		}
		Integer dictCode = Integer.parseInt(dictCodeStr);
		renderJson(DataDict.getSubDict(dictCode));
	}

	/**
	 * 合同类型字典表：下拉框
	 */
	public void selDictContract() throws Exception {
		// 参数 dictCode 优先于 filter_EQ_dictCode
		Map<String, Object> userParams = new HashMap<String, Object>();
		userParams.put("EQ_userId", WebUtils.getUserId());
		List rsList = user2orgLogic.qryOrgByUserIdAndParms(userParams);
		Set<Integer> set = contractLogic.getContractType(rsList);
		String dictCodeStr = request.getParameter("dictCode");
		if (StringUtils.isEmpty(dictCodeStr)) {
			Map searchParam = WebUtils.getParametersStartingWith(request);
			dictCodeStr = searchParam.get("EQ_dictCode").toString();
		}
		Integer dictCode = Integer.parseInt(dictCodeStr);
		List<Dict> dicts = DataDict.getSubDict(dictCode);
		List<Dict> dicte = new ArrayList<Dict>();
		for (Integer integer : set) {
			for (Dict dict : dicts) {
				if (integer == dict.getValue())
					dicte.add(dict);
			}
		}
		renderJson(dicte);
	}

	/**
	 * 字典表：下拉框
	 */
	public void selDictByParentId() throws Exception {
		Long parentId = Long.parseLong(request.getParameter("parentId"));
		renderJson(DataDict.getDictByParentId(parentId));
	}

	public String init() throws Exception {
		jsPath.add("/js/console/dict/Q.dict.js");

		String[] authorities = { "D_DICT_LIST", "D_DICT_ADD", "D_DICT_EDIT" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	public void getJson() {
		Map searchParam = WebUtils.getParametersStartingWith(request);
		Page page = new Page(start, limit, sort, dir);
		page = appLogic.findAll(page, searchParam);
		renderJson(page);
	}

	public void save() {
		appLogic.persist(model);
		appLogic.loadDict();// 重新加载数据字典
		dealJson(true);
	}

	public void edit() {
		model = appLogic.findById(id);
		dealJson(true, DataUtils.toJson(model));
	}

	public void update() {
		appLogic.merge(model);
		appLogic.loadDict();// 重新加载数据字典
		dealJson(true);
	}

}