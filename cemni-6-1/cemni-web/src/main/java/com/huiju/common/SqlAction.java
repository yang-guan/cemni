package com.huiju.common;

import java.util.Map;

import javax.ejb.EJB;

import com.huiju.afterservice.rightmaint.logic.RightMaintRemote;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
/**
 * 站内信相关服务
 * @author zzy
 * @date 2017年4月10日 下午3:04:01
 *
 */
public class SqlAction extends BaseAction<Object, String> {
	private static final long serialVersionUID = 1L;
	@EJB
	private SqlRemote SqlLogic;

	@EJB(mappedName = "RightMaintBean")
	private RightMaintRemote rightMaintLogic;
	/**
	 * 
	 * 站内信-客户权益单
	 *
	 * @author zzy
	 * @date 2017年4月10日 下午3:02:31
	 */
	public void queryRightMaintAudit() throws Exception {
		Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
		searchParam.put("start", start);
		searchParam.put("limit", limit);
		renderJson(SqlLogic.queryRightMaintAudit(searchParam));
	}
	/**
	 * 
	 * 站内信-活动管理
	 *
	 * @author zzy
	 * @date 2017年4月10日 下午3:02:31
	 */
	public void queryActivity() throws Exception {
		Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
		searchParam.put("start", start);
		searchParam.put("limit", limit);
		renderJson(SqlLogic.queryActivity(searchParam));
	}
	/**
	 * 
	 * 站内信-合同管理
	 *
	 * @author zzy
	 * @date 2017年4月10日 下午3:02:31
	 */
	public void queryContract() throws Exception {
		Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
		searchParam.put("start", start);
		searchParam.put("limit", limit);
		renderJson(SqlLogic.queryContract(searchParam));
	}
	/**
	 * 
	 * 站内信-个人客户
	 *
	 * @author zzy
	 * @date 2017年4月10日 下午3:02:31
	 */
	public void queryIndividcust() throws Exception {
		Map<String, Object> searchParam = WebUtils.getParametersStartingWith(request);
		searchParam.put("start", start);
		searchParam.put("limit", limit);
		renderJson(SqlLogic.queryIndividcust(searchParam));
	}

}