package com.huiju.competitor.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.huiju.module.data.eao.GenericEao;
import com.huiju.module.data.logic.GenericLogicImpl;
import com.huiju.competitor.eao.AdDeployEaoLocal;
import com.huiju.competitor.entity.AdDeploy;

@Stateless(mappedName = "AdDeployBean")
public class AdDeployBean extends GenericLogicImpl<AdDeploy, Long> implements AdDeployRemote {
	@EJB(mappedName = "AdDeployEaoBean")
	private AdDeployEaoLocal competitorADEao;

	@Override
	protected GenericEao<AdDeploy, Long> getGenericEao() {
		return competitorADEao;
	}
}