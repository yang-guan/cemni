package com.huiju.contract.logic;

import java.io.File;
import java.util.Map;

import javax.ejb.Remote;
import com.huiju.module.data.logic.GenericLogic;
import com.huiju.contract.entity.ContractContact;

@Remote
public interface ContractContactRemote extends GenericLogic<ContractContact, Long> {
	public Map<String, Object> excel(File file,Long contractId);
}