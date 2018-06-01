package com.huiju.contract.logic;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.huiju.contract.entity.Contract;
import com.huiju.module.data.logic.GenericLogic;

@Remote
public interface ContractRemote extends GenericLogic<Contract, Long> {

	/**
	 * 合同联系人预警：短信通知
	 * 
	 * @author：yuhb
	 * @date：2017年3月28日 上午12:21:36
	 */
	void contractWarning();

	/**
	 * 
	 * 合同权限控制
	 * @author：guonianlun
	 * @date：2017年3月30日09:37:51
	 */
	@SuppressWarnings("rawtypes")
	Set<Integer> getContractType(List rsList);
}