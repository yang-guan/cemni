package com.huiju.contract.terms.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.contract.terms.eao.ContractTermsEaoLocal;
import com.huiju.contract.terms.entity.ContractTerms;

@Stateless(mappedName = "ContractTermsBean")
public class ContractTermsBean extends GenericLogicImpl<ContractTerms, Long> implements ContractTermsRemote {
	@EJB(mappedName = "ContractTermsEaoBean")
	private ContractTermsEaoLocal contractTermsEao;

	@Override
	protected GenericEao<ContractTerms, Long> getGenericEao() {
		return contractTermsEao;
	}
}