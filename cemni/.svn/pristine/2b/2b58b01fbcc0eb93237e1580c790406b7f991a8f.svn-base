package com.huiju.contract;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;

import com.huiju.common.DataDict;
import com.huiju.common.GlobalConst;
import com.huiju.common.sql.logic.SqlRemote;
import com.huiju.console.dict.logic.DictRemote;
import com.huiju.console.user2org.logic.User2orgRemote;
import com.huiju.contract.entity.Contract;
import com.huiju.contract.entity.ContractAdmin;
import com.huiju.contract.entity.ContractBrand;
import com.huiju.contract.entity.ContractCom;
import com.huiju.contract.entity.ContractContact;
import com.huiju.contract.entity.ContractExp;
import com.huiju.contract.entity.ContractFee;
import com.huiju.contract.entity.ContractOp;
import com.huiju.contract.entity.ContractPay;
import com.huiju.contract.logic.ContractAdminRemote;
import com.huiju.contract.logic.ContractBrandRemote;
import com.huiju.contract.logic.ContractComRemote;
import com.huiju.contract.logic.ContractContactRemote;
import com.huiju.contract.logic.ContractExpRemote;
import com.huiju.contract.logic.ContractFeeRemote;
import com.huiju.contract.logic.ContractOpRemote;
import com.huiju.contract.logic.ContractPayRemote;
import com.huiju.contract.logic.ContractRemote;
import com.huiju.contract.terms.entity.ContractTerms;
import com.huiju.contract.terms.logic.ContractTermsRemote;
import com.huiju.module.data.Page;
import com.huiju.module.data.util.DataUtils;
import com.huiju.module.fs.entity.FileInfo;
import com.huiju.module.fs.logic.FileInfoRemote;
import com.huiju.module.util.StringUtils;
import com.huiju.module.web.action.BaseAction;
import com.huiju.module.web.util.WebUtils;
import com.huiju.permission.logic.UserAuthGroupRemote;
import com.huiju.sms.sms.logic.SmsRemote;
import com.huiju.utils.NeuUtils;

@SuppressWarnings({ "rawtypes" })
public class ContractAction extends BaseAction<Contract, Long> {
	private static final long serialVersionUID = 1L;
	private Long termsId;
	private Long contactId;
	private File file;
	private String fileName;

	@EJB
	private ContractRemote contractLogic;
	@EJB
	private DictRemote dictLogic;
	@EJB
	private ContractTermsRemote ContractTermsLogic;
	@EJB
	private ContractFeeRemote ContractFeeLogic;
	@EJB
	private ContractPayRemote ContractPayLogic;
	@EJB
	private UserAuthGroupRemote userAuthGroupLogic;
	@EJB
	private ContractAdminRemote contractAdminLogic;
	@EJB
	private ContractBrandRemote contractBrandLogic;
	@EJB
	private ContractComRemote contractComLogic;
	@EJB
	private ContractExpRemote contractExpLogic;
	@EJB
	private ContractOpRemote contractOpLogic;
	@EJB
	private SqlRemote sqlLogic;
	@EJB
	private ContractContactRemote contractContactLogic;
	@EJB
	private FileInfoRemote fileInfoLogic;
	@EJB
	private SmsRemote smsLogic;
	@EJB
	private User2orgRemote user2orgLogic;

	public String init() {
		jsPath.add("/js/contract/Q.contract.js");

		String[] authorities = { "D_CONTRACT_LIST", "D_CONTRACT_ADD", "D_CONTRACT_SEARCH", "D_CONTRACT_DELETE",
				"D_CONTRACT_EDIT" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	public String contactInit() {
		jsPath.add("/js/contract/Q.contract.contact.js");

		String[] authorities = { "D_CONTRACT_CONTACT_LIST", "D_CONTRACT_CONTACT_SEARCH", "D_CONTRACT_CONTACT_LINKMAN" };
		permissions = this.checkPermissions(authorities);
		return LIST;
	}

	public void getJson() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		Map<String, Object> userParams = new HashMap<String, Object>();
		userParams.put("EQ_userId", WebUtils.getUserId());
		List rsList = user2orgLogic.qryOrgByUserIdAndParms(userParams);
		Set<Integer> set = contractLogic.getContractType(rsList);
		String strParams = StringUtils.join(set.toArray(), ",");
		params.put("IN_contractType", strParams);
		Page<Contract> page = new Page<Contract>(start, limit, NeuUtils.chgQrySort(sort), dir);
		page = contractLogic.findAll(page, params);
		for (Contract dt : page) {
			dt.setContractTypeName(DataDict.getDictName(DataDict.CONTRACT_CONTRACT, dt.getContractType()));
		}
		String excludes[] = { "contractFees", "contractPays", "contractAdmins", "contractBrands", "contractComs",
				"contractExps", "contractOps" };
		renderJson(DataUtils.toJson(page, excludes));
	}

	public void save() {
		this.setOneToManyValue();
		model.setContractNum(sqlLogic.getCnNum(GlobalConst.NUM_CONTRACT));
		model.setCreateUser(WebUtils.getUserName());
		model.setCreateDate(Calendar.getInstance());
		model = contractLogic.persist(model);
		dealJson(true);
	}

	private void setOneToManyValue() {
		// 设置付款日期记录
		if (null != this.model.getContractPays()) {
			for (ContractPay cp : this.model.getContractPays()) {
				cp.setContract(model);
			}
		}
		// 设置工费金损记录
		if (null != this.model.getContractFees()) {
			for (ContractFee cf : this.model.getContractFees()) {
				cf.setContract(model);
			}
		}
		// 行政部信息
		if (null != this.model.getContractAdmins()) {
			for (ContractAdmin ca : this.model.getContractAdmins()) {
				ca.setContract(model);
			}
		}
		// 品牌部信息
		if (null != this.model.getContractBrands()) {
			for (ContractBrand cb : this.model.getContractBrands()) {
				cb.setContract(model);
			}
		}
		// 商品部信息
		if (null != this.model.getContractComs()) {
			for (ContractCom cc : this.model.getContractComs()) {
				cc.setContract(model);
			}
		}
		// 拓展部信息
		if (null != this.model.getContractExps()) {
			for (ContractExp ce : this.model.getContractExps()) {
				ce.setContract(model);
			}
		}
		// 运营部信息
		if (null != this.model.getContractOps()) {
			for (ContractOp co : this.model.getContractOps()) {
				co.setContract(model);
			}
		}
	}

	public void delete() {
		for (Long id : ids) {
			contractLogic.removeById(id);
		}
		dealJson(true);
	}

	public void edit() {
		model = contractLogic.findById(id);
		String excludes[] = { "contractFees", "contractPays", "contractAdmins", "contractBrands", "contractComs",
				"contractExps", "contractOps" };
		dealJson(true, DataUtils.toJson(model, excludes));
	}

	public void update() {
		model = contractLogic.merge(model);
		dealJson(true);
	}

	public void getAllPay() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = ContractPayLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 获取子表所有关联工费金损记录
	 * 
	 * @return
	 */
	public void getAllFee() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = ContractFeeLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 合同--行政部
	 * 
	 * @return
	 */
	public void getAllAdmin() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = contractAdminLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 合同-商品部
	 */
	public void getAllCom() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = contractComLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 合同-品牌部
	 */
	public void getAllBrand() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = contractBrandLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 合同-拓展部
	 */
	public void getAllExp() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = contractExpLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 合同-运营部
	 */
	public void getAllOp() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = contractOpLogic.findAll(params);
		renderJson(rsList, "contract");
	}

	/**
	 * 获取所有条款记录
	 */
	public void getTerms() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		List rsList = ContractTermsLogic.findAll(params);
		renderJson(rsList);
	}

	/**
	 * 合同联系人
	 */
	public void getAllContact() {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request);
		Page<ContractContact> page = new Page<ContractContact>(start, limit);
		page = contractContactLogic.findAll(page, params);
		for (ContractContact dt : page) {
			dt.setTypeName(DataDict.getDictName(DataDict.CONTRACT_LINKMAN_TYPE, dt.getType()));
		}
		renderJson(page, "contract");
	}

	public void deleteContact() {
		contractContactLogic.removeById(contactId);
		dealJson(true);
	}

	/**
	 * 根据条款id查询条款内容
	 */
	public void getTermsContent() {
		ContractTerms terms = ContractTermsLogic.findById(termsId);
		renderJson(terms);
	}

	/**
	 * 导入联系人
	 */
	public void excelContact() {
		Long contractId = request.getParameter("contractId") == null ? 0 : Long.parseLong(request
				.getParameter("contractId"));

		Map<String, Object> retMap = null;
		try {
			FileInfo fileInfo = fileInfoLogic.upload(fileName, file);
			retMap = contractContactLogic.excel(fileInfoLogic.convert(fileInfo).getFile(), contractId);
		} catch (Exception e) {
			retMap = new HashMap<String, Object>();
			retMap.put("success", false);
			retMap.put("msg", e.getMessage());
		}
		renderHtml(DataUtils.toJson(retMap));
	}

	public Long getTermsId() {
		return termsId;
	}

	public void setTermsId(Long termsId) {
		this.termsId = termsId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}